package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * represents the camera, point of view on the geometries
 * 
 * @author ayala
 *
 */
public class Camera 
{
	/**
	 *  location is center of camera location
	 *  Vtoward points to view's center.
	 *  Vright
	 *  Vup
	 */
	private Point3D location;
	private Vector Vtoward;
	private Vector Vup;
	private Vector Vright;
	
	
	/**
	 * @param _location location of camera
	 * @param _Vright 
	 * @param _Vup
	 * creates vector Vtoward, orthogonal to Vright and Vup
	 */
	public Camera(Point3D _location, Vector _Vright, Vector _Vup) 
	{
		if (_Vright.length() != 1)
			_Vright = _Vright.normalized();
		if (_Vup.length() != 1)
			_Vup = _Vup.normalized();
		this.location = new Point3D(_location);
		this.Vright = new Vector(_Vright);
		this.Vup = new Vector(_Vup);
		this.Vtoward = new Vector(Vup.crossProduct(Vright).normalize());
	}
	
	
	/**
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return
	 */
	public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
		return null;
	}

	
}
