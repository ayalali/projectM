package geometries;

import static primitives.Util.isZero;

import primitives.*;

/**
 * 
 * Cylinder class represents Cylinder in 3D Cartesian coordinate
 * system
 * 
 * @author ayala and naama
 *
 */
public class Cylinder extends Tube{

	/**
	 * _height represents the length of the cylinder
	 */
	private double _height;
	
	
	/**
	 * @param _radius represents the cylinder radius.
	 * @param _axisRay represents the cylinder direction.
	 * @param _height represents the length of the cylinder.
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height) {
		super(_radius, _axisRay);
		this._height = _height;
	}

	/**
	 * @return the length of the cylinder.
	 */
	public double get_height() {
		return _height;
	}
	
	/**
	 * @return description of fields values.
	 */
	@Override
	public String toString() {
		return "[" + super.toString() + "Cylinder [_height=" + _height + "]]";
	}

	/**
	 *@return the vertical vector to the cylinder on the point p.
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector n = null;
		if(p.distance(this.get_axisRay().get_point())<this.get_radius())
		{
			n = new Vector(p.add(this.get_axisRay().get_direction()));
		}
		else if(p.distance(this.get_axisRay().get_point().add(this.get_axisRay().get_direction().scale(_height)))<this.get_radius()) 
		{
			n = new Vector(p.add(this.get_axisRay().get_direction()));
		}
		else
		{
			Point3D p0 = this.get_axisRay().get_point();
	    	Vector v = this.get_axisRay().get_direction();
	    	//t = v (P – P0)
	    	double t = p.subtract(p0).dotProduct(v);
	    	// O = P0 + tv.
	    	Point3D o=null;
	    	if (isZero(t))// if it's close to 0, we'll get ZERO vector exception
	        {
	    		throw new IllegalArgumentException("ERROR: Tube.getNormal: t is zero");
	        }
	    	o = p0.add(v.scale(t));
			n = p.subtract(o).normalize();
		}
		return new Vector(n);
	}

//	/**
//     * @author Dan Zilberstein
//     * @param point point to calculate the normal
//     * @return normal
//     */
//    @Override
//    public Vector getNormal(Point3D point) {
//        Point3D o = _ray.getPoint();
//        Vector v = _ray.getDirection();
//
//        // projection of P-O on the ray:
//        double t;
//        try {
//            t = alignZero(point.subtract(o).dotProduct(v));
//        } catch (IllegalArgumentException e) { // P = O
//            return v;
//        }
//
//        // if the point is at a base
//        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
//            return v;
//
//        o = o.add(v.scale(t));
//        return point.subtract(o).normalize();
//    }
	


	
}