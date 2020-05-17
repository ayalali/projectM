package renderer;

import primitives.*;
import scene.Scene;
import primitives.Util;
import java.util.List;

import elements.Camera;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

/**
 * includes all functions to render the image
 * 
 * @author ayala
 *
 */
public class Render 
{
	
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
				
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);

				if (intersectionPoints == null)
				{	_imageWriter.writePixel(j, i, background);}
				else
				{
					closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			} //_imageWriter.writePixel(j-1, i-1, calcColor(closestPoint).getColor());

		}
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
	 * @param p point and its geometry
	 * @return the color of the point = Ia * Ka + Ie + Kd * |l*n| * Il + Ks * (max(0, -v*r))^ Nsh * Il
	 * 
	 */
	public Color calcColor(GeoPoint p) 
	{
		List<LightSource> lights = _scene.get_lights();
		
		// ambient light (Ia * Ka)
		Color color = _scene.get_ambientLight().get_intensity(); 
		
		// add emission of geometry (Ie)
		color = color.add(p._geometry.get_emmission()); 
		
		//if there is no source lights
		if (_scene.get_lights() == null)  
		{
			return color;
		}
		
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
            	//r = l - 2 (l*n) * n
    			Vector r = Lvector.subtract(n.scale(2* Lvector.dotProduct(n)));
            	
            	//add diffuse
            	color = color.add(diffuse(Kd, nl, Il));
            	
            	//add specular
    			color = color.add(specular(Ks, v, r, Nsh, Il));
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
}
