package renderer;

import primitives.*;
import static primitives.Util.*;


import scene.Scene;
import primitives.Util;
import java.util.List;


import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

/**
 * includes all functions to render the image
 * 
 * @author ayala and naama
 *
 */
public class Render 
{
	
	/**
	 * Permanent for stop condition in recursion
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * Permanent for stop condition in recursion
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;
	
	/**
	 * Permanent to move the start of the rays for shadow, reflection and refraction rays
	 */
	private static final double DELTA = 0.1;
	
	/**
	 * write the pixels into a file
	 */
	ImageWriter _imageWriter;
	/**
	 * contains all the elements in the image
	 */
	Scene _scene;
	
	/**
	 * Render constructor
	 * 
	 * @param imageWriter 
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	/**
	 * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer of pixels
	 */
	public void renderImage() {
		
		GeoPoint closestPoint = null;
		
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		java.awt.Color background = _scene.get_background().getColor();
		
		double  distance = _scene.get_distance();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		
		for(int i = 0; i < _imageWriter.getNx(); i++) 
		{
			for(int j = 0; j < _imageWriter.getNy(); j++) 
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
				
				closestPoint = findClosestIntersection(ray);
				
				if (closestPoint == null)
				{	_imageWriter.writePixel(j, i, background);}
				else
				{
					_imageWriter.writePixel(j, i, calcColor(closestPoint, ray).getColor());
				}
			}

		}
	}
	
	
	/**
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray)
	{
		 Color color = calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0);
	     color = color.add(_scene.get_ambientLight().get_intensity());
	     return color;
	}
	
	
	/**
	 * Ia * Ka = ambient light
	 * 
	 * Ie = emmission light of the geometry
	 * 
	 * Kd * |l*n| * Il = diffuse light
	 * 
	 * Ks * (max(0, -v*r))^ Nsh * Il = specular light
	 * 
	 * r = l - 2 (l*n) * n
	 * 
	 * Si = shadow parameter, if 0 it means the light can't get to that point (and there is a shadow). 
	 * 
	 * Kr * Ir = reflection color
	 * 
	 * Kt * It = transparency color
	 * 
	 * @param p point and its geometry
	 * @param ray 
	 * @param level
	 * @param k
	 * @return the color of the point 
	 * Ia * Ka + Ie + sumI(Kd * |l*n| * Il + Ks * (max(0, -v*r))^ Nsh) * Il * Si + Kr * Ir + Kt * It
	 * 
	 */
	public Color calcColor(GeoPoint p, Ray ray, int level, double k) 
	{
		if (level == 0 || k < MIN_CALC_COLOR_K) 
		{
			return Color.BLACK;
		}
		List<LightSource> lights = _scene.get_lights();
		
		// add emission of geometry (Ie)
		Color color = p._geometry.get_emmission(); 
		
		Material material = p._geometry.get_material();
		double Kd = material.get_kD();
		double Ks = material.get_kS();
		double Nsh = material.get_nShininess();
		
		//geometry normal
		Vector n = p._geometry.getNormal(p._point);
		
		//Vto of camera - vector toward the view plane
		Vector v = _scene.get_camera().getVtoward();
		
		for (LightSource l : lights) 
		{
			//light direction:
			Vector Lvector = l.getL(p._point); 
			
			//light intensity:
			Color Il = l.getIntensity(p._point);
			
			double nl = Util.alignZero(n.dotProduct(Lvector));
            double nv = Util.alignZero(n.dotProduct(v));

            //only if they have the same sign
            
            if ((nl > 0 && nv > 0) || (nl < 0 && nv < 0))
            {
            	if(unshaded(l, Lvector, n, p))
            	{
            		
            		//r = l - 2 (l*n) * n
            		Vector r = Lvector.subtract(n.scale(2* Lvector.dotProduct(n)));
            	
            		//add diffuse
            		color = color.add(diffuse(Kd, nl, Il));
            	
            		//add specular
            		color = color.add(specular(Ks, v, r, Nsh, Il));
            	}
            }
		}//end lights loop
		
		if (level == 1) 
		{
			return Color.BLACK;
		}
		
		double kr = p._geometry.get_material().get_kR();
		double kkr = k * kr;
		
		if (kkr > MIN_CALC_COLOR_K) 
		{
			Ray reflectedRay = reflectedRay(ray, p);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null) 
			{
				color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
			}
		}
		
		double kt = p._geometry.get_material().get_kT();
		double kkt = k * kt;
		
		if (kkt > MIN_CALC_COLOR_K)
		{
			Ray refractedRay = refractedRay(ray, p);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null) 
			{
				color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
			}
		}
		
		return new Color(color);
	}
	
	/**
	 * @param Kd diffuse power (material field)
	 * @param l light direction (normalized vector, getL)
	 * @param n normal vector to the geometry
	 * @param Il getIntensity of the light
	 * 
	 * @return diffuse light color of one light object on specific point = Kd * |l*n| * Il
	 */
	public Color diffuse(double Kd, double nl, Color Il)
	{
		return Il.scale(Kd* Math.abs(nl));
	}
	
	/**
	 * @param Ks specular power (material field)
	 * @param v Vto of camera (normalized vector)
	 * @param r specular vector (l hits the geometry, 
	 * and r get out from hit point in the opposite direction).
	 * r = l - 2 (l*n) * n
	 * @param Nsh n shininess of material (how much its a shiny material)
	 * @param Il getIntensity of the light
	 * 
	 * @return specular light color of one light object on specific point = Ks * (max(0, -v*r))^ Nsh * Il
	 */
	public Color specular(double Ks, Vector v, Vector r, double Nsh, Color Il)
	{
		double vr = (-1) * Util.alignZero(v.dotProduct(r));
		if (vr <= 0) 
		{
			return new Color(0,0,0); //Black
		}
		return Il.scale(Ks * Math.pow(vr, Nsh));
	}
	
	/**
	 * In the intersectionPoints - find the point with minimal distance from the ray 
	 * begin point (now it is just the camera location) and return it
	 * 
	 * @param points the list of the intersection points
	 * @return the closest point to the ray
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> points) {
		GeoPoint clstPoint = null;
        double minDst = Double.MAX_VALUE;

        Point3D p0 = this._scene.get_camera().getLocation();

        for (GeoPoint pt: points ) {
            double distance = p0.distance(pt._point);
            if (distance < minDst){
            	minDst = distance;
                clstPoint = new GeoPoint(pt._geometry,pt._point);
            }
        }
        return  clstPoint;
	}
	
	/**
	 * Add to the image grid without changing the the picture
	 * 
	 * @param interval the space of the grid
	 * @param color the color of the grid
	 */
	public void printGrid(int interval, java.awt.Color color){
		for(int j = 0; j < _imageWriter.getNy(); j++) {
			for(int i = 0; i < _imageWriter.getNx(); i++) {
				if (j % interval == 0 || i % interval == 0) {
					_imageWriter.writePixel(i, j, color);
                }
			}		
		}
	}
	
	/**
	 * Function writeToImage produces unoptimized jpeg file of
	 * the image according to pixel color matrix in the directory
	 * of the project
	 */
	public void writeToImage() {
		_imageWriter.writeToImage();
	}
	
	
	/**
	 * @param light - light source
	 * @param l - light direction
	 * @param n - geometry normal
	 * @param gp - the point of the geometry
	 * @return if the point is not shaded
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
		
		Vector lightDiraction = l.scale(-1);
		Vector delta = n.scale(n.dotProduct(lightDiraction)>0?DELTA:-DELTA);
		Point3D point = gp._point.add(delta);
		Ray lightRay = new Ray(point, lightDiraction, n);
		
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		
		if (intersections == null)
		{	
			return true;
		}
		
		double lightDistance = light.getDistance(gp._point);
		
		for (GeoPoint g : intersections) {
			if (alignZero(g._point.distance(gp._point) - lightDistance) <= 0 && gp._geometry.get_material().get_kT() == 0)
					return false;
		}
		return true;

	}
	
	/**
	 * @param r ray sent toward geometry
	 * @param g includes geometry and intersection point (between ray and geometry).
	 * 
	 * 
	 * this function need to be called only if there is 
	 * at least one intersection point with ray r.
	 * 
	 * @return reflected ray = v-2(v*n)*n
	 * 
	 */
	private Ray reflectedRay(Ray r, GeoPoint g)
	{
		Vector v = r.get_direction();
		Point3D p = g._point;
		Vector n = g._geometry.getNormal(p);
		
		double temp = v.dotProduct(n); //(v*n)
		if (temp == 0) 
		{
			return null;
		}
		
		Vector vector = v.subtract(n.scale(2 * temp));//v-2(v*n)*n
		return new Ray(p, vector, n);
	}
	
	/**
	 * @param r ray sent toward the geometry
	 * @param geoPoint geometry and it intersection point
	 * @return refracted ray.
	 * direction = r direction (normalized), 
	 * point = intersect point + normal * DELTA
	 */
	private Ray refractedRay (Ray r, GeoPoint geoPoint)
	{
		return new Ray(geoPoint._point, r.get_direction(), geoPoint._geometry.getNormal(geoPoint._point));
	}
	
	/**
	 * @param ray ray sent toward geometry
	 * @return GeoPoint which includes the closest intersection point,
	 * using function findIntersections.
	 * 
	 */
	private GeoPoint findClosestIntersection(Ray ray)
	{
		if (ray == null) 
		{
            return null;
        }
		
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D p = ray.get_point();

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
        if (intersections == null)
        {
            return null;
        }

        for (GeoPoint geoPoint : intersections) 
        {
            double distance = p.distance(geoPoint._point);
            if (distance < closestDistance) 
            {
                closestPoint = new GeoPoint(geoPoint._geometry, geoPoint._point);
                closestDistance = distance;
            }
        }
        return closestPoint;
	}
}
