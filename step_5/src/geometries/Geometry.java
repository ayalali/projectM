package geometries;

import primitives.*;

/**
 * interface that binding the common functions of 
 * geometry package classes.
 * 
 * 
 * @author ayala and naama
 *
 */
public interface Geometry extends Intersectable {

	/**
	 * @param p = point on the geometry.
	 * 
	 * @return the vertical vector from the 3D point p.
	 */
	Vector getNormal(Point3D p);
	
}