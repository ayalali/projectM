package primitives;

/**
 * Point3D presents a 3D cartesian coordinate point.
 * 
 * 
 * @author ayala
 *
 */
public class Point3D
{
	
	/**
	 * origion of 3D Cartesian coordinate system
	 */
	public static Point3D ZERO = new Point3D(0,0,0);
	
	/**
	 * three coordinates together presents a 3D point
	 */
	private Coordinate _x;
	private Coordinate _y;
	private Coordinate _z;

	
	/**
	 * @param x first coordinate
	 * @param y second coordinate
	 * @param z third coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) 
	{
		this._x = new Coordinate(x);
		this._y = new Coordinate(y);
		this._z = new Coordinate(z);
	}
	
	/**
	 * @param x first coordinate
	 * @param y second coordinate
	 * @param z third coordinate
	 */
	public Point3D(double x, double y, double z) 
	{
		this._x = new Coordinate(x);
		this._y = new Coordinate(y);
		this._z = new Coordinate(z);
	}

	/**
	 * @param p 3D cartesian coordinate point
	 */
	public Point3D(Point3D p) 
	{
		this._x = new Coordinate(p._x);
		this._y = new Coordinate(p._y);
		this._z = new Coordinate(p._z);
	}

	
	/**
	 * @return first coordinate
	 */
	public Coordinate get_x() 
	{
		return new Coordinate(_x);
	}
	/**
	 * @return second coordinate
	 */
	public Coordinate get_y() 
	{
		return new Coordinate(_y);
	}
	/**
	 * @return third coordinate
	 */
	public Coordinate get_z() 
	{
		return new Coordinate(_z);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Point3D [_x=" + _x + ", _y=" + _y + ", _z=" + _z + "]";
	}

	
	/**
	 * @param p 3D cartesian coordinate point.
	 * @return length of distance between p(a1,a2,a3) and this point(b1,b2,b3)
	 * 
	 * 
	 * (a1-b1)^2 + (a2-b2)^2 + (a3-b3)^2
	 * 
	 * 
	 * 
	 */
	public double distanceSquared(Point3D p)
	{
		return (get_x().get()-p.get_x().get()) * (get_x().get()-p.get_x().get()) +
			   (get_y().get()-p.get_y().get()) * (get_y().get()-p.get_y().get()) +
			   (get_z().get()-p.get_z().get()) * (get_z().get()-p.get_z().get());
	}
	
	/**
	 * @param p 3D cartesian coordinate point.
	 * @return power length of distance between p(b1,b2,b3) and this point(a1,a2,a3) by using the function distanceSquared
	 * 
	 * 
	 * distance = sqrt[(a1-b1)^2 + (a2-b2)^2 + (a3-b3)^2]
	 * 
	 */
	public double distance(Point3D p)
	{
		return Math.sqrt(distanceSquared(p));
	}
	
	/**
	 * @param vector 3D vector in cartesian coordinate system.
	 * @return mathematical add of 3D point(a1,a2,a3) and vector(b1,b2,b3)
	 * 
	 * 
	 * add = (a1+b1, a2+b2, a3+b3)
	 * 
	 */
	public Point3D add(Vector vector)
	{
		return new Point3D(_x.get() + vector.get_head().get_x().get(),_y.get() + vector.get_head().get_y().get(),_z.get() + vector.get_head().get_z().get());
	}
	
//	public Point3D add(Point3D p)
//	{
//		return new Point3D(_x.get() + p.get_x().get()
//				,_y.get() + p.get_y().get(),_z.get() + p.get_z().get());
//	}
	
	
	/**
	 * @param p 3D cartesian coordinate point.
	 * @return mathematical subtract of 3D point(a1,a2,a3) and vector(b1,b2,b3)
	 * 
	 * 
	 * add = (a1-b1, a2-b2, a3-b3)
	 * 
	 */
	public Vector subtract(Point3D p)
	{
		return new Vector(_x.get()-p._x.get(), _y.get()-p._y.get(), _z.get()-p._z.get());
	}
}