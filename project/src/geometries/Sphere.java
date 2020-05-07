package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Sphere represents a 3D sphere, that is a redial geometry.
 * 
 * @author ayala and naama
 *
 */
public class Sphere extends RadialGeometry{
	
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
	 * @param color the color of the sphere
	 * @param r the radius of the sphere
	 * @param c is the center of the sphere by 3D Cartesian coordinate
	 */
	public Sphere(Color color, double r, Point3D c) {
		this(r,c);
		this._emmission = new Color(color);
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

	
	/**
	 * @param r ray sent toward sphere
	 * @return o-2 points intersections
	 * 
	 * P0 = ray's point
	 * 
	 * L = length from ray's beginning to sphere's center = center - ray.head
	 * 
	 * V = vector from ray's point to sphere's center = _center.subtract(P)
	 * 
	 * tm = length of the ray to the point perpendicular to the sphere's center
	 *	  = L * W = L.dotproduct(V)
	 * 
	 * D = perpendicular length from tm to sphere's center
	 *	 = sqrt(L.length^2 - tm^2)
	 *
	 * if D > sphere's radius there is no intersections.
	 * if D = radius:
	 * there is two intersections.
	 * 
	 * th = length from first point on sphere to radius
	 * 	  = sqrt(radius^2 - D^2)
	 *
	 * t1 = tm - th
	 * t2 = tm + th
	 * 
	 * P1 = ray.head + t1 * V
	 * P2 = ray.head + t2 * V
	 *
	 * if D == radius:
	 * there is only one intersection
	 * 
	 * P1 = ray.head + t1 * V
	 *
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray r) {
		
		List<GeoPoint> lst = new ArrayList<GeoPoint>();
		Point3D P0 = r.get_point();
		Vector V = new Vector(r.get_direction()).normalize();
		Vector L;
		try 
		{
			L = new Vector(_center.subtract(P0));//.normalize();
		}
		catch (Exception e)
		{
			lst.add(new GeoPoint(this,(r.getPoint(get_radius()))));
			return lst;
		}
		double tm = V.dotProduct(L);
		double D = Math.sqrt(L.length()*L.length() - tm*tm);
		if (D > get_radius())
		{
			return null;
		}
		double thSquared = get_radius()*get_radius() - D*D;
		double th;
		if (thSquared <= 0)
			return null;
		th = Math.sqrt(thSquared);
		double t1 = tm - th;
		double t2 = tm + th;
		if (t1 > 0)
		{
			GeoPoint P1 = new GeoPoint(this, r.getPoint(t1));
			lst.add(P1);
		}
		if(D == get_radius())
			return lst;
		if (t2 > 0)
		{
			GeoPoint P2 = new GeoPoint(this, r.getPoint(t2));
			lst.add(P2);
		}
		if (lst.isEmpty())
			return null;		
		return lst;
	}


}