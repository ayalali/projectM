package geometries;

import java.util.ArrayList;
import java.util.List;

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
	 *
	 *
	 *
	 *
	 *
	 */
	@Override
	public List<Point3D> findIntersections(Ray r) {
		ArrayList<Point3D> lst = new ArrayList<Point3D>();
		Vector L = new Vector(_center);
		Vector helpVector = new Vector(r.get_direction());			
		helpVector.normalize();
		double tm = L.dotProduct(helpVector);
		double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));
		if (d > get_radius()) 
		{
			return lst;
		}
		double th = Math.sqrt(Math.pow(get_radius(), 2) - Math.pow(d, 2));
		double t1 = tm - th;
		double t2 = tm + th;
		Vector help1Vector = new Vector(helpVector);
		help1Vector.scale(t1);
		lst.add(new Point3D(help1Vector.get_head()));
		if (d == get_radius()) 
		{
			return lst;
		}
		Vector help2Vector = new Vector(helpVector);
		help2Vector.scale(t2);
		lst.add(help2Vector.get_head());
		return lst;
	}


}