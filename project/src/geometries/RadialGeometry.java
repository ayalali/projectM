package geometries;

import primitives.*;

/**
 * RadialGeometry containing the properties that exist in
 * round geometries.
 * 
 * @author ayala
 *
 */
public abstract class RadialGeometry extends Geometry 
{

	//fields
	
	/**
	 * radius of the radial geometry
	 */
	private double _radius;
	
	
	//constructors
	
	/**
	 * @param c
	 * @param m
	 * @param _radius
	 */
	public RadialGeometry(Color c, Material m, double _radius) 
	{
		this(_radius);
		this._emmission = new Color(c);
		this._material = new Material(m);
	}
	/**
	 * @param _radius - radius of the geometry
	 */
	public RadialGeometry(double _radius) 
	{
		super();
		this._radius = _radius;
	}
	/**
	 * @param r - RadialGeometry entity
	 */
	public RadialGeometry(RadialGeometry r) 
	{
		this._radius = r._radius;
		this._emmission = new Color(r._emmission);
		this._material = new Material(r._material);
	}
	
	
	//getters
	
	/**
	 * @return the radius of the geometry
	 */
	public double get_radius() {
		return _radius;
	}

	
	//other functions
	
	/**
	 *@return vertical vector to the geometry on the 3D point p.
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}