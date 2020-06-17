package geometries;

import primitives.*;

/**
 * Geometry class is the basic class for all the geometries.
 * implements the getNormal function.
 * 
 * 
 * @author ayala and naama
 *
 */
public abstract class Geometry implements Intersectable 
{
	
	//fields
	
	/**
	 * emmission color
	 */
	protected Color _emmission;
	/**
	 * geometry's material
	 */
	protected Material _material;
	
	
	
	//getters
	
	/**
	 * @return geometry's material
	 */
	public Material get_material() 
	{
		return _material;
	}
	/**
	 * @return the emmission of the geometry
	 */
	public Color get_emmission() 
	{
		return _emmission;
	}

	
	//constructors
	
	public Geometry(Color emmission) 
	{
		this(emmission,new Material(0, 0, 0));
	}
	
	public Geometry(Color _emmission, Material _material) 
	{
		this._emmission = _emmission;
		this._material = _material;
	}

	public Geometry() {
		this(Color.BLACK,new Material(0,0,0));
	}

	//other functions
	
	/**
	 * @param p = point on the geometry.
	 * 
	 * @return the vertical vector from the 3D point p.
	 */
	public abstract Vector getNormal(Point3D p);
	
}