package geometries;

import primitives.*;

/**
 * Cylinder class represents Cylinder in 3D Cartesian coordinate
 * system
 * 
 * @author ayala and naama
 *
 */
public class Cylinder extends Tube{

	/**
	 * _height represents the length of the cylinder
	 */
	private double _height;
	
	
	/**
	 * @param _radius represents the cylinder radius.
	 * @param _axisRay represents the cylinder direction.
	 * @param _height represents the length of the cylinder.
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height) {
		super(_radius, _axisRay);
		this._height = _height;
	}

	/**
	 * @return the length of the cylinder.
	 */
	public double get_height() {
		return _height;
	}
	
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
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}



	
}