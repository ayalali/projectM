package elements;

import primitives.*;

/**
 * interface between lights of different types.
 * 
 * @author ayala and naama
 * 
 */
public interface LightSource 
{

	
	/**
	 * @param p point3D
	 * @return geometry's color on point p
	 */
	public Color getIntensity(Point3D p);
	
	/**
	 * @param p point3D
	 * @return light's direction
	 */
	public Vector getL(Point3D p);
	
	/**
	 * @param point point3D
	 * @return the distance between the point and the light source
	 */
	double getDistance(Point3D point);
}
