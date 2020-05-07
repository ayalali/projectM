package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

import elements.Camera;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

public class Render {
	
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
	 * Returns the color of one 3D point
	 * 
	 * @param p point
	 * @return the color of the point
	 */
	public Color calcColor(GeoPoint p) {
		return _scene.get_ambientLight().get_intensity().add(p._geometry.get_emmission());
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
