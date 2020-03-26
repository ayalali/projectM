package primitives;
//cool comment


/**
 * Vector presents a vector in 3D cartesian coordinate system
 * 
 * 
 * @author ayala
 *
 */
public class Vector 
{
	
	/**
	 * direction of the vector, where is he header
	 */
	Point3D _head;
	
	
	/**
	 * @param head direction of the vector, where is he header
	 * 
	 * Point3D format
	 */
	public Vector(Point3D head)
	{
		_head = new Point3D(head);
		if (head == new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0))) {
			throw new IllegalArgumentException("you cant create a zero vector.");
		}
	}
	/**
	 * @param v vector in 3D cartesian coordinate system
	 */
	public Vector(Vector v)
	{
		_head = new Point3D(v._head);
	}
	/**
	 * @param a first coordinate of the vector
	 * @param b second coordinate of the vector
	 * @param c third coordinate of the vector
	 */
	public Vector(double a, double b, double c)
	{
		this._head = new Point3D(new Coordinate(a), new Coordinate(b), new Coordinate(c));
	}
	/**
	 * @param a first coordinate of the vector
	 * @param b second coordinate of the vector
	 * @param c third coordinate of the vector
	 */
	public Vector(Coordinate a, Coordinate b, Coordinate c)
	{
		this._head = new Point3D(new Coordinate(a), new Coordinate(b), new Coordinate(c));
	}
//	public Vector(Point3D p1, Point3D p2)
//	{
//		Coordinate x = new Coordinate(p2.get_x().get()-p1.get_x().get());
//		Coordinate y = new Coordinate(p2.get_y().get()-p1.get_y().get());
//		Coordinate z = new Coordinate(p2.get_z().get()-p1.get_z().get());
//		_head = new Point3D(x, y, z);
//	}


	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other._head))
			return false;
		return true;
	}
	
	
	public String toString() 
	{
		return "" + _head;
	}

	
	/**
	 * @param v vector in 3D cartesian coordinate system
	 * @return mathematical subtract between this vector(a1,a2,a3) and v(b1,b2,b3)
	 * 
	 * sub = (a1-b1,a2-b2,a3-b3)
	 * 
	 */
	public Vector subtract(Vector v)
	{
		return new Vector(_head.get_x().get()-v.get_head().get_x().get(),_head.get_y().get()-v.get_head().get_y().get(),_head.get_z().get()-v.get_head().get_z().get());
	}
	
	/**
	 * @param v vector in 3D cartesian coordinate system
	 * @return mathematical add between this vector(a1,a2,a3) and v(b1,b2,b3)
	 * 
	 * 
	 * add = (a1+b1,a2+b2,a3+b3)
	 * 
	 */
	public Vector add(Vector v)
	{
		return new Vector(_head.get_x().get()+v.get_head().get_x().get(),_head.get_y().get()+v.get_head().get_y().get(),_head.get_z().get()+v.get_head().get_z().get());
	}
	
	/**
	 * @param t relation number
	 * @return mathematical scale mult between vector(a,b,c) and t
	 * 
	 * 
	 * scale = (t*a, t*b, t*c)
	 * 
	 */
	public Vector scale(double t)
	{
		return new Vector(_head.get_x().get()*t,_head.get_y().get()*t,_head.get_z().get()*t);
	}
	
	/**
	 * @param vector vector in 3D cartesian coordinate system
	 * @return mathematical product betwen two vectors: this(a1,b1,c1) and vector(a2,b2,c2). relation number.
	 * 
	 * 
	 * dotProduct = a1*a2 + b1*b2 + c1*c2
	 * 
	 */
	public double dotProduct(Vector vector)
	{
		return (this._head.get_x().get() * vector._head.get_x().get() + this._head.get_y().get() * vector._head.get_y().get() + this._head.get_z().get() * vector._head.get_z().get());
	}
	
	/**
	 * @param v vector in 3D cartesian coordinate system
	 * @return mathematical cross product between two vectors: this(a,b,c) and v(d,e,f)
	 * 
	 * 
	 * i = b*f - c*e
	 * j = a*f - c*d
	 * k = a*e - b*d
	 * 
	 * crossProduct = (i, -j, k)
	 * 
	 */
	public Vector crossProduct(Vector v)
	{
		double i = _head.get_y().get() * v._head.get_z().get() - _head.get_z().get() * v._head.get_y().get(); 
		double j = (-1)*(_head.get_x().get() * v._head.get_z().get() - _head.get_z().get() * v._head.get_x().get()); 
		double k = _head.get_x().get() * v._head.get_y().get() - _head.get_y().get() * v._head.get_x().get(); 
		return new Vector (new Point3D(new Coordinate(i), new Coordinate(j), new Coordinate(k)));
	}
	
	public double lengthSquared() 
	{
		return (_head.get_x().get()*_head.get_x().get() + _head.get_y().get()*_head.get_y().get() + _head.get_z().get()*_head.get_z().get());
	}
	
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	
	public Vector normalize()
	{
		double l = length();
		this.scale(1/l);
		return this;
	}
	
	public Vector normalized()
	{
		Vector help = new Vector(this);
		return help.normalize();
	}
	
	public Point3D get_head() {
		return new Point3D(_head);
	}
}