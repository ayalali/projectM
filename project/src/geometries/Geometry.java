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
public abstract class Geometry implements Intersectable {

	protected Color _emmission;
	
	/**
	 * @return the emmission of the geometry
	 */
	public Color get_emmission() {
		return _emmission;
	}

	public Geometry(Color emmission) {
		this._emmission = new Color(emmission);
	}
	
	public Geometry() {
		this(Color.BLACK);
	}

	/**
	 * @param p = point on the geometry.
	 * 
	 * @return the vertical vector from the 3D point p.
	 */
	public abstract Vector getNormal(Point3D p);
	
}