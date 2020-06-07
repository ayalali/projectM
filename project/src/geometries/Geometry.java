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
		return new Material(_material.get_kD(), _material.get_kS(), _material.get_nShininess());
	}
	/**
	 * @return the emmission of the geometry
	 */
	public Color get_emmission() 
	{
		return new Color(_emmission);
	}

	
	//constructors
	
	public Geometry(Color emmission) 
	{
		this._emmission = new Color(emmission);
		this._material = new Material(0, 0, 0);
	}
	
	public Geometry(Color _emmission, Material _material) 
	{
		this._emmission = new Color(_emmission);
		this._material = new Material(_material.get_kD(), _material.get_kS(), _material.get_nShininess(), _material.get_kT(), _material.get_kR());
	}

	public Geometry() {
		this(Color.BLACK);
		this._material = new Material(0,0,0);
	}

	//other functions
	
	/**
	 * @param p = point on the geometry.
	 * 
	 * @return the vertical vector from the 3D point p.
	 */
	public abstract Vector getNormal(Point3D p);
	
}