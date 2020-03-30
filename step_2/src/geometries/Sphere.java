package geometries;

import primitives.*;

/**
 * Sphere represents a 3D sphere, that is a redial geometry.
 * 
 * @author ayala and naama
 *
 */
public class Sphere extends RadialGeometry{
	////
	/**
	 * the center of the sphere by 3D Cartesian coordinate.
	 */
	private Point3D _center;

	/**
	 * @param _radius the radius of the sphere
	 * @param _center is the center of the sphere by 3D Cartesian coordinate
	 */
	public Sphere(double _radius, Point3D _center) {
		super(_radius);
		this._center = new Point3D(_center);
	}

	/**
	 * @return the center of the sphere by 3D Cartesian coordinate
	 */
	public Point3D get_center() {
		return new Point3D(_center);
	}

	/**
	 *@return the sphere fields values
	 */
	@Override
	public String toString() {
		return "[ " + super.toString() + "Sphere [_center=" + _center + "]]";
	}
	
	
	/**
	 *@return vertical vector to the sphere on the 3D Cartesian coordinate p
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return new Vector(p.subtract(this._center)).normalize();
	}


}