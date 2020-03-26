package primitives;


public class Ray 
{
	//fields
	Point3D _point;
	Vector _direction;
	
	//constructors
	public Ray(Point3D _point, Vector _direction) 
	{
		this._point = new Point3D(_point);
		this._direction = new Vector(_direction.normalized());
	}
	
	public Ray(Ray r)
	{
		this._point = new Point3D(r._point);
		this._direction = new Vector(r._direction);
	}
	
	//get
	public Point3D get_point() 
	{
		return new Point3D(_point);
	}
	
	public Vector get_direction() 
	{
		return new Vector(_direction);
	}
	
	
	//equals
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

	//toString
	public String toString() 
	{
		return "Ray [_point=" + _point + ", _direction=" + _direction + "]";
	}
	
	
}