/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * 
 * 
 * @author ayala and naama
 *
 */
public interface Intersectable 
{
	/**
	 * @param r 
	 * @return List of points that the ray hit on
	 */
	public List<Point3D> findIntersections(Ray r);
	
}
