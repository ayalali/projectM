package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * Triangle represents a two-dimensional triangle in 3D Cartesian coordinate
 * 
 * @author ayala and naama
 *
 */
public class Triangle extends Polygon {
	
	
	/**
	 * @param vertices list of vertices according to their order by edge path
	 * 
	 * 
	 */
	public Triangle(Point3D... vertices) {
		super(vertices);
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		return super.getNormal(p).normalized();
	}
}