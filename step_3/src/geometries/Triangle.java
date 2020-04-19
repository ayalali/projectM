package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
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
	
	@Override
	public List<Point3D> findIntersections(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}
}