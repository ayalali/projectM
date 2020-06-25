package geometries;

import java.util.List;
import primitives.Util;
import primitives.Color;
import primitives.Material;
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
public class Triangle extends Polygon 
{
	
	//constructors
	
	/**
	 * @param vertices list of vertices according to their order by edge path
	 * 
	 * 
	 */
	public Triangle(Point3D... vertices) {
		super(vertices);
	}
	/**
	 * @param c the color of the triangle
	 * @param vertices list of vertices according to their order by edge path
	 */
	public Triangle(Color c, Point3D... vertices) {
		this(vertices);
		this._emmission = new Color(c);
	}
	/**
	 * @param m triangle's material
	 * @param c triangle's color
	 * @param vertices triangle's vertices
	 */
	public Triangle(Color c, Material m, Point3D... vertices) 
	{
		this(c, vertices);
		this._material = new Material(m);
	}
	
	//other functions
	
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
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findIntersections(ray);
        
        if (intersections == null) return null;

        Point3D p0 = new Point3D(ray.get_point());
        Vector v = new Vector(ray.get_direction()).normalize();

        Vector v1 = new Vector(_vertices.get(0).subtract(p0)).normalize();
        Vector v2 = new Vector(_vertices.get(1).subtract(p0)).normalize();
        Vector v3 = new Vector(_vertices.get(2).subtract(p0)).normalize();

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (Util.isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (Util.isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (Util.isZero(s3)) return null;

        //intersections.get(0)._geometry = this;
        for (GeoPoint geo : intersections) 
        {
            geo._geometry = this;
        }
        
        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
	
	@Override
	public String toString()
	{
		return "triangle";
	}
}



