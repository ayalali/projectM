package geometries;

import java.util.List;
import primitives.Util;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * 
 * Triangle represents a two-dimensional triangle in 3D Cartesian coordinate
 * 
 * @author ayala and naama
 *
 */
public class Triangle extends Polygon {
	
	
	/**
	 * @param vertices list of vertices according to their order by edge path
	 * 
	 * 
	 */
	public Triangle(Point3D... vertices) {
		super(vertices);
	}
	
	/**
	 *@param p point on the geometry
	 *@return vector normal on that point
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return super.getNormal(p).normalized();
	}
	
	/**
	*@param ray send toward geometry
	*return list of points intersections
	*
	* check if there is intersection at the triangle's plane,
	* and then check if the intersection is on  the triangle.
	*
	*/
	@Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        System.out.println(_plane.toString());
        if (intersections == null) return null;

        Point3D p0 = new Point3D(ray.get_point());
        Vector v = new Vector(ray.get_direction());

        Vector v1 = new Vector(_vertices.get(0).subtract(p0));
        Vector v2 = new Vector(_vertices.get(1).subtract(p0));
        Vector v3 = new Vector(_vertices.get(2).subtract(p0));

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (Util.isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (Util.isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (Util.isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
}



/*@Override
public List<Point3D> findIntersections(Ray r) 
{
	Point3D _p1 = new Point3D(_vertices.get(0));
	Point3D _p2 = new Point3D(_vertices.get(1));
	Point3D _p3 = new Point3D(_vertices.get(2));
	Point3D P0 = r.get_point();
	Vector v1 = new Vector(_p1.subtract(P0));
	Vector v2 = new Vector(_p2.subtract(P0));
	Vector v3 = new Vector(_p3.subtract(P0));			
	Vector n1 = v1.crossProduct(v2);
	Vector n2 = v3.crossProduct(v1);
	Vector n3 = v2.crossProduct(v3);
	n1.normalize();
	n2.normalize();
	n3.normalize();
	
	Vector inTheTriangle1 = new Vector(_p2.subtract(_p1));
	Vector inTheTriangle2 = new Vector(_p3.subtract(_p1));
	Vector normal = new Vector(inTheTriangle1.crossProduct(inTheTriangle2));
	Plane Tplane = new Plane(_p1, normal);
	List<Point3D> lst = Tplane.findIntersections(r);
	if(lst == null)
		return null;
	Point3D P = new Point3D(lst.get(0));
	double a = n1.dotProduct(new Vector(P.subtract(P0)));
	double b = n2.dotProduct(new Vector(P.subtract(P0)));
	double c = n3.dotProduct(new Vector(P.subtract(P0)));
	if (((a>=0) && (b>=0) && (c>= 0)) || ((a<0) && (b<0) && (c<0)))
	{
		return lst;
	}
	return null;
}*/

