package renderer;

import primitives.*;
import static primitives.Util.*;

import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

/**
 * includes all functions to render the image
 * 
 * @author ayala and naama
 *
 */
public class Render {

	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 */
	private class Pixel 
	{
		private long _maxRows = 0; // Ny
		private long _maxCols = 0; // Nx
		private long _pixels = 0; // Total number of pixels: Nx*Ny
		public volatile int row = 0; // Last processed row
		public volatile int col = -1; // Last processed column
		private long _counter = 0; // Total number of pixels processed
		private int _percents = 0; // Percent of pixels processed
		private long _nextCounter = 0; // Next amount of processed pixels for percent progress


		/**
		 * The constructor for initializing the main follow up Pixel object
		 * 	@param maxRows the amount of pixel rows
		 * 	@param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) 
		{
			_maxRows = maxRows;_maxCols = maxCols; _pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}


		/**
		 * Default constructor for secondary Pixel objects
		 */
		public Pixel() {}


		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) 
		{
			int percents = nextP(target);
			if (_print && percents > 0) System.out.printf("\r %02d%%", percents);
			if (percents >= 0) return true;
			if (_print) System.out.printf("\r %02d%%", 100);
			return false;
		}


		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) 
		{
			++col; ++_counter;
			if (col < _maxCols) 
			{
				target.row = this.row; target.col = this.col;
				if (_print && _counter == _nextCounter) 
				{
					++_percents;_nextCounter = _pixels * (_percents + 1) / 100; return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) 
			{
				col = 0;
				if (_print && _counter == _nextCounter) 
				{
					++_percents; _nextCounter = _pixels * (_percents + 1) / 100; return _percents;
				}
				return 0;
			}
			return -1;
		}
	}

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


	ImageWriter _imageWriter;
	Scene _scene;

	/**
	 * Render constructor
	 * 
	 * @param imageWriter write the pixels into a file
	 * @param scene contains all the elements in the image
	 */
	public Render(ImageWriter imageWriter, Scene scene) 
	{
		_imageWriter = imageWriter;
		_scene = scene;
	}

	/**
	 * Filling the buffer according to the geometries that are in the scene.
	 * This function does not creating the picture file, but create the buffer of pixels
	 */
	public void renderImage() 
	{
		
		Camera camera = _scene.get_camera();
		double  distance = _scene.get_distance();

		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();

		final Pixel thePixel = new Pixel(nY, nX); // Main pixel management object
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) 
		{
			// create all threads
			threads[i] = new Thread(() -> 
			{
				// Auxiliary thread’s pixel object
				Pixel pixel = new Pixel(); 
				while (thePixel.nextPixel(pixel))
				{
					ArrayList<Ray> rays = (camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row, distance, width, height, 9));
					_imageWriter.writePixel(pixel.col, pixel.row, averageColor(rays).getColor());
				}
			});
		}
		for (Thread thread : threads) thread.start(); // Start all the threads
		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
		
		
//			for(int i = 0; i < _imageWriter.getNx(); i++) 
//			{
//				for(int j = 0; j < _imageWriter.getNy(); j++) 
//				{
//					Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
//
//					closestPoint = findClosestIntersection(ray);
//
//					if (closestPoint == null)
//					{	_imageWriter.writePixel(j, i, background);}
//					else
//					{
//						_imageWriter.writePixel(j, i, calcColor(closestPoint, ray).getColor());
//					}
//				}
//
//			}
	}


	private Color averageColor (ArrayList<Ray> rays)
	{
		int r = 0, g = 0, b = 0;
		Color color;
		java.awt.Color background = _scene.get_background().getColor();
		for (Ray ray : rays) 
		{
			GeoPoint closestPoint = findClosestIntersection(ray);
			if (closestPoint == null)					
			{	
				r += background.getRed();
				g += background.getGreen();
				b += background.getBlue();
			}
			else
			{
				color = calcColor(closestPoint, ray);
				r += color.getColor().getRed();
				g += color.getColor().getGreen();
				b += color.getColor().getBlue();
			}
		}
		
		r/= rays.size();
		g/= rays.size();
		b/= rays.size();
		return new Color(r, g, b);
	}
	
	/**
	 * @param gp point and its geometry
	 * @param ray
	 * @return the color of the point 
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

		if (level == 0 || k < MIN_CALC_COLOR_K) {
			return Color.BLACK;
		}

		List<LightSource> lights = _scene.get_lights();

		// add emission of geometry (Ie)
		Color color = p._geometry.get_emmission(); 

		Material material = p._geometry.get_material();
		double Kd = material.get_kD();
		double Ks = material.get_kS();
		double Nsh = material.get_nShininess();
		double kr = p._geometry.get_material().get_kR();
		double kt = p._geometry.get_material().get_kT();
		double kkt = k * kt;
		double kkr = k * kr;

		//geometry normal
		Vector n = p._geometry.getNormal(p._point).normalize();

		//Vto of camera - vector toward the view plane
		Vector v = p._point.subtract(_scene.get_camera().getLocation()).normalize();
		//Vector v = _scene.get_camera().getVtoward().normalize();

		for (LightSource l : lights) 
		{
			//light direction:
			Vector Lvector = l.getL(p._point);

			double nl = Util.alignZero(n.dotProduct(Lvector));
			double nv = Util.alignZero(n.dotProduct(v));

			//only if they have the same sign

			if ((nl > 0 && nv > 0) || (nl < 0 && nv < 0))
			{
				double ktr = transparency(l, Lvector, n, p);
				if(ktr * k > MIN_CALC_COLOR_K)
				{
					Color Il = l.getIntensity(p._point).scale(ktr);

					//r = l - 2 (l*n) * n
					Vector r = Lvector.subtract(n.scale(2* Lvector.dotProduct(n))).normalize();

					//
					Color lightIntensity = l.getIntensity(p._point).scale(ktr);

					//add diffuse
					color = color.add(diffuse(Kd, nl, Il));

					//add specular
					color = color.add(specular(Ks, v, r, Nsh, Il));
				}
			}
		}//end lights loop

		if (level == 1) return Color.BLACK;



		if (kkr > MIN_CALC_COLOR_K) 
		{
			Ray reflectedRay = reflectedRay(p._point, ray, n);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null) 
			{
				color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
			}
		}



		if (kkt > MIN_CALC_COLOR_K)
		{
			Ray refractedRay = refractedRay(p._point, ray, n);
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
			if (alignZero(g._point.distance(gp._point) - lightDistance) <= 0 /*&& gp._geometry.get_material().get_kT() == 0*/)
				return false;
		}
		return true;

	}


	/**
	 * @param ls current light source
	 * @param l light's vector
	 * @param n normal vector on the geometry point
	 * @param geopoint point and geometry
	 * @return number between zero to one, which is shadow's power.
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint)
	{
		//we sends ray from point to light source
		Vector lightDiraction = l.scale(-1).normalize();
		Vector delta = n.scale(n.dotProduct(lightDiraction)>0? DELTA:-DELTA);
		Point3D point = geopoint._point.add(delta);
		Ray lightRay = new Ray(point, lightDiraction, n);

		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);

		//there is no geometries between the point and the light source
		if (intersections == null)
		{	
			return 1.0;
		}

		double lightDistance = ls.getDistance(geopoint._point);
		double ktr = 1.0;
		for (GeoPoint g : intersections) {
			//if the intersection is really between the light and the point
			if (alignZero(g._point.distance(geopoint._point) - lightDistance) <= 0)
			{		ktr *= g._geometry.get_material().get_kT();
			if (ktr < MIN_CALC_COLOR_K) 
			{
				return 0.0;
			}
			}
		}
		return ktr;
	}

	/**
	 * @param pointGeo normal to the geometry
	 * @param ray the point of the geometry
	 * @param n ray sent toward the geometry
	 * 
	 * this function need to be called only if there is 
	 * at least one intersection point with ray r.
	 * 
	 * @return reflected ray = v-2(v*n)*n
	 * 
	 */
	private Ray reflectedRay(Point3D point, Ray r, Vector n)
	{
		Vector v = r.get_direction().normalized();
		double vn = v.dotProduct(n); //(v*n)

		if (vn == 0) return null;

		Vector vector = v.subtract(n.scale(2 * vn));//v-2(v*n)*n
		return new Ray(point, vector, n);
	}


	/**
	 * @param n normal to the geometry
	 * @param point the point of the geometry
	 * @param r ray sent toward the geometry
	 * 
	 * 
	 * @return refracted ray.
	 * 
	 * 
	 * direction = r direction (normalized), 
	 * point = intersect point + normal * DELTA
	 */
	private Ray refractedRay (Point3D point, Ray r, Vector n)
	{
		return new Ray(point, r.get_direction().normalized(), n);
	}

	/**
	 * @param ray ray sent toward geometry
	 * 
	 * @return GeoPoint which includes the closest intersection point,
	 *
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

	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less SPARE (2) is taken
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) 
	{
		if (threads < 0) throw new IllegalArgumentException("Multithreading must be 0 or higher");
		if (threads != 0) _threads = threads;
		else 
		{
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			_threads = cores <= 2 ? 1 : cores;
		}
		return this;
	}

	/**
	 *  Set debug printing on
	 * @return the Render object itself
	 */
	public Render setDebugPrint() { _print = true; return this; }
}
