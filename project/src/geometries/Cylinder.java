package geometries;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

import primitives.*;

/**
 * 
 * Cylinder class represents Cylinder in 3D Cartesian coordinate
 * system
 * 
 * @author ayala and naama
 *
 */
public class Cylinder extends Tube
{

	//fields
	
	/**
	 * _height represents the length of the cylinder
	 */
	private double _height;
	
	
	//constructor
	
	/**
	 * @param _radius represents the cylinder radius.
	 * @param _axisRay represents the cylinder direction.
	 * @param _height represents the length of the cylinder.
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height) {
		super(_radius, _axisRay);
		this._height = _height;
	}

	
	//getters
	
	/**
	 * @return the length of the cylinder.
	 */
	public double get_height() {
		return _height;
	}
	
	
	//other functions
	
	/**
	 * @return description of fields values.
	 */
	@Override
	public String toString() {
		return "[" + super.toString() + "Cylinder [_height=" + _height + "]]";
	}

	/**
	 *@return the vertical vector to the cylinder on the point p.
	 */
	@Override
	public Vector getNormal(Point3D point) 
	{
		Point3D o = this.get_axisRay().get_point();
		Vector v = this.get_axisRay().get_direction();

		// projection of P-O on the ray:
		double t;
		try {
			t = alignZero(point.subtract(o).dotProduct(v));
		} catch (IllegalArgumentException e) { // P = O
			return v;
		}

		// if the point is at a base
		if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
			return v;

		o = o.add(v.scale(t));
		return point.subtract(o).normalize();
	}
	
	
}