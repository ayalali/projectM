package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * represents the camera, point of view on the geometries
 * 
 * @author ayala and naama
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
	private Point3D _p0;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;
	
	
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
		this._p0 = new Point3D(_location);
		this._vRight = new Vector(_Vright);
		this._vUp = new Vector(_Vup);
		this._vTo = new Vector(_vUp.crossProduct(_vRight).normalize());
	}
	
	/**
	 * @return the location of the camera
	 */
	public Point3D getLocation() {
		return new Point3D(_p0);
	}
	/**
	 * @return the vector toward
	 */
	public Vector getVtoward() {
		return new Vector(_vTo);
	}
	/**
	 * @return the vector up
	 */
	public Vector getVup() {
		return new Vector(_vUp);
	}
	/**
	 * @return the vector right
	 */
	public Vector getVright() {
		return new Vector(_vRight);
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
