package primitives;

import static primitives.Util.*;


/**
 * Ray class represent ray which is a point and direction vector.
 * 
 * @author ayala and naama
 *
 */
public class Ray 
{
	
	/**
	 * The head of the ray
	 */
	Point3D _point;
	
	/**
	 * The direction of the ray
	 */
	Vector _direction;
	
	/**
	 * Permanent to move the start of the rays for shadow, reflection and refraction rays
	 */
	private static final double DELTA = 0.1;
	
	
	//constructors
	
	/**
	 * Ray constructor based on Point3D and Vector
	 * 
	 * @param _point the head of the ray
	 * @param _direction the direction of the ray
	 */
	public Ray(Point3D _point, Vector _direction) 
	{
		this._point = new Point3D(_point);
		this._direction = new Vector(_direction.normalized());
	}
	
	/**
	 * Ray's copy constructor
	 * 
	 * @param r Ray
	 */
	public Ray(Ray r)
	{
		this._point = new Point3D(r._point);
		this._direction = new Vector(r._direction);
	}
	
	/**
	 * @param p ray point
	 * @param direction ray direction
	 * @param n normal
	 */
	public Ray(Point3D p, Vector direction, Vector n)
	{
		double temp = n.dotProduct(direction);
		double delta = DELTA;
		if (temp <= 0) 
		{
			delta = -delta;
		}
		Vector deltaNormal = n.scale(delta);
		
		_point = p.add(deltaNormal);
		_direction = new Vector(direction).normalized();
	}
	
	/**
	 * @return the head of the ray
	 */
	public Point3D get_point() 
	{
		return new Point3D(_point);
	}
	
	/**
	 * @return the direction of the ray
	 */
	public Vector get_direction() 
	{
		return new Vector(_direction);
	}
	
	
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_direction == null) {
			if (other._direction != null)
				return false;
		} else if (!_direction.equals(other._direction))
			return false;
		if (_point == null) {
			if (other._point != null)
				return false;
		} else if (!_point.equals(other._point))
			return false;
		return true;
	}
	
	public String toString() 
	{
		return "Ray [_point=" + _point + ", _direction=" + _direction + "]";
	}
	
	 /**
     * @param length
     * @return new Point3D P = P0 + t*V
     */
    public Point3D getPoint(double t) {
    	if(isZero(t))
    		return _point;
    	return new Point3D(_point.add(_direction.scale(t)));
    }
	
}