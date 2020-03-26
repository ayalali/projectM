package geometries;

import primitives.*;

/**
 * Tube represents a 3D tube by ray and radius.
 * 
 * 
 * @author ayala
 *
 */
public class Tube extends RadialGeometry{

	/**
	 * the tube direction
	 */
	private Ray _axisRay;

	/**
	 * @param _radius the radius of the sphere
	 * @param _axisRay tube direction
	 */
	public Tube(double _radius, Ray _axisRay) {
		super(_radius);
		this._axisRay = new Ray(_axisRay);
	}

	/**
	 * @return tube direction
	 */
	public Ray get_axisRay() {
		return new Ray(_axisRay);
	}

	/**
	 *@return the tube fields values
	 */
	@Override
	public String toString() {
		return "[ " + super.toString() + "Tube [_axisRay=" + _axisRay + "]]";
	}
	
	/**
	 *@return vertical vector to the tube on the 3D point p
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	
}