package geometries;

import primitives.*;

/**
 * RadialGeometry containing the properties that exist in
 * round geometries.
 * 
 * @author ayala
 *
 */
public abstract class RadialGeometry extends Geometry {

	/**
	 * radius of the radial geometry
	 */
	private double _radius;
	
	/**
	 * @param _radius - radius of the geometry
	 */
	public RadialGeometry(double _radius) {
		this._radius = _radius;
	}

	/**
	 * @param r - argument of RadialGeometry
	 */
	public RadialGeometry(RadialGeometry r) 
	{
		this._radius = r._radius;
	}
	
	/**
	 * @return the radius of the geometry
	 */
	public double get_radius() {
		return _radius;
	}

	
	/**
	 *@return vertical vector to the geometry on the 3D point p.
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}