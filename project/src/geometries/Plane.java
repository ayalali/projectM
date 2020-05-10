package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * plane represents a 2D surface in 3D Cartesian coordinate
 *
 *
 * @author ayala and naama
 *
 */
public class Plane extends Geometry 
{

	//fields
	 
	/**
	* _p is 3D cartesian coordinate on the plane
	*
	* both fields represents together a plane by mathematical definition.
	*/
	private Point3D _p;
	/**
	 * _normal is a vertical vector to the plane
	 */
	private Vector _normal;

	
	//constructors
	
	/**
	*
	*
	*/
	public Plane(Color _emmission, Material _material, Point3D p1, Point3D p2, Point3D p3) {
		this(p1, p2, p3);
		this._emmission = new Color(_emmission);
		this._material = new Material(_material);
	}
	/**
	* @param p1 3D cartesian coordinate on the plane
	* @param p2 3D cartesian coordinate on the plane
	* @param p3 3D cartesian coordinate on the plane
	*
	* the constructor calculates the normal and save one of the
	* points in order to represent the plane with vector and point.
	*/
	public Plane(Point3D p1, Point3D p2, Point3D p3) 
	{
		super();
		_p = new Point3D(p1);
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		Vector v = v1.crossProduct(v2);
		_normal = v.normalized();
	}
	/**
	 * @param _emmission geometry's color
	 * @param _material geometry's material
	 * @param _p 3D Cartesian coordinate on the plane
	 * @param _normal vertical vector to the plane
	 */
	public Plane(Color _emmission, Material _material, Point3D _p, Vector _normal) 
	{
		super(_emmission, _material);
		this._p = new Point3D(_p);
		this._normal = new Vector(_normal);
	}
	/**
	* @param p - 3D Cartesian coordinate on the plane
	* @param v - vertical vector to the plane
	*/
	public Plane(Point3D p, Vector v)
	{
		super();
		_p = new Point3D(p);
		_normal = new Vector(v);
	}
	/**
	 * @param c - Plane's color
	 * @param p - 3D Cartesian coordinate on the plane
	 * @param v - vertical vector to the plane
	 */
	public Plane(Color c, Point3D p, Vector v)
	{
		this(p,v);
		this._emmission = new Color(c);
		this._material = new Material(0,0,0);
	}
	
	
	//getters
	
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
	
	
	//other functions
	
	/**
	* @return vertical vector to the plane, on the 3D point p.
	*/
	@Override
	public Vector getNormal(Point3D p) {
		return new Vector(_normal).normalize();
	}
	
	/**
	*@param r ray that sent toward geometry
	*@return List with intersections points
	*
	* V = vector from ray's beginning to plane's point = ray.point - plane.point
	*
	* nv = plane's normal * ray's direction, if nv = zero there is no intersections.
	*
	* t = (plane's normal * V)/nv
	*
	* if t <= 0 there is no intersections
	* else P = ray's point + t * ray's direction
	*
	*/
	@Override
	public List<GeoPoint> findIntersections(Ray r) {
		Vector V;
        try
        {
            V = new Vector(_p.subtract(r.get_point()));
        }
        catch (IllegalArgumentException e)
        {
            return null; // ray starts from point Q - no intersections
	    }
	
	    double nv = _normal.dotProduct(r.get_direction());
	    if (isZero(nv)) // ray is parallel to the plane - no intersections
	        return null;
	
	    double t = alignZero(_normal.dotProduct(V) / nv);
	
	    return t <= 0 ? null : List.of(new GeoPoint(this, r.getPoint(t)));
	}
}
