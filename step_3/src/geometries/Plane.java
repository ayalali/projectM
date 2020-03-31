package geometries;

import primitives.*;

/**
 * plane represents a 2D surface in 3D Cartesian coordinate
 * 
 * 
 * @author ayala and naama
 *
 */
public class Plane implements Geometry {
	////
	/**
	 * _p is 3D cartesian coordinate on the plane
	 * _normal is a vertical vector to the plane
	 * 
	 * together they are represents a plane by mathematical definition.
	 */
	private Point3D _p;
	private Vector _normal;
	
	/**
	 * @param p1 3D cartesian coordinate on the plane
	 * @param p2 3D cartesian coordinate on the plane
	 * @param p3 3D cartesian coordinate on the plane
	 * 
	 * the constructor calculates the normal and save one of the 
	 * points in order to represent the plane with vector and point.
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		_p = new Point3D(p1);
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		Vector v = v1.crossProduct(v2);
		_normal = v.normalized();
	}
	
	/**
	 * @param p - 3D cartesian coordinate on the plane 
	 * @param v - vertical vector to the plane
	 */
	public Plane(Point3D p, Vector v)
	{
		_p = new Point3D(p);
		_normal = new Vector(v);
	}

	/**
	 * @return 3D cartesian coordinate on the plane
	 */
	public Point3D getP() { 
		return new Point3D(_p); 
	}

	/**
	 * @return vertical vector to the plane
	 */
	public Vector getNormal() {
		return new Vector(_normal);
	}

	
	/**
	 * @return vertical vector to the plane, on the 3D point p.
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return new Vector(_normal).normalize();
	}

}