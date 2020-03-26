package geometries;

import primitives.Point3D;

/**
 * 
 * Triangle represents a two-dimensional triangle in 3D Cartesian coordinate
 * 
 * @author ayala
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
	
	
}