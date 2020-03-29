package geometries;
import static java.lang.System.out;
import static primitives.Util.*;
import primitives.*;

/**
 * Tube represents a 3D tube by ray and radius.
 * 
 * 
 * @author ayala
 *
 */
public class Tube extends RadialGeometry{

	/**
	 * the tube direction
	 */
	private Ray _axisRay;

	/**
	 * @param _radius the radius of the sphere
	 * @param _axisRay tube direction
	 */
	public Tube(double _radius, Ray _axisRay) {
		super(_radius);
		this._axisRay = new Ray(_axisRay);
	}

	/**
	 * @return tube direction
	 */
	public Ray get_axisRay() {
		return new Ray(_axisRay);
	}

	/**
	 *@return the tube fields values
	 */
	@Override
	public String toString() {
		return "[ " + super.toString() + "Tube [_axisRay=" + _axisRay + "]]";
	}
	
	/**
	 *@return vertical vector to the tube on the 3D point p
	 *
	 *
	 *n = normalize(P - O)
	 *O is projection of P on cylinder's ray:
	 *t = v (P – P0)
	 *O = P0 + tv
	 *
	 *
	 */
    @Override
    public Vector getNormal(Point3D p) {
   
    	Point3D p0 = _axisRay.get_point();
    	Vector v = _axisRay.get_direction();
    	//t = v (P – P0)
    	double t = p.subtract(p0).dotProduct(v);
    	// O = P0 + tv.
    	Point3D o=null;
    	if (isZero(t))// if it's close to 0, we'll get ZERO vector exception
        {
    		throw new IllegalArgumentException("ERROR: Tube.getNormal: t is zero");
        }
    	o = p0.add(v.scale(t));
    	Vector n = p.subtract(o).normalize();
    	return n;
    }
}