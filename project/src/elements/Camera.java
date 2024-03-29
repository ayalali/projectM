package elements;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import renderer.Render;
import primitives.Util;

/**
 * represents the camera, point of view on the geometries
 * 
 * @author ayala and naama
 *
 */
public class Camera 
{
	/**
	 *  p0 is center of camera location
	 */
	private Point3D _p0;
	/**
	 *  Vup pointed up
	 */
	private Vector _vUp;
	/**
	 * Vtoward points to view's plane center.
	 * 
	 */
	private Vector _vTo;
	/**
	 * _vRight pointed to the right side of camera
	 */
	private Vector _vRight;


	/**
	 * @param _location location of camera
	 * @param _Vright 
	 * @param _Vup
	 * creates vector Vtoward, orthogonal to Vright and Vup
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) 
	{
		if (vUp.dotProduct(vTo) != 0)
			throw new IllegalArgumentException("the vectors must be orthogonal");

		this._p0 = new Point3D(p0);
		this._vTo = vTo.normalized();
		this._vUp = vUp.normalized();

		_vRight = this._vTo.crossProduct(this._vUp).normalize();
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
	 * @param nX num of pixels of X line
	 * @param nY num of pixels of Y line
	 * @param j last coordinate of target pixel
	 * @param i first coordinate of target pixel
	 * @param screenDistance distance between screen and camera
	 * @param screenWidth width of screen - length
	 * @param screenHeight height of screen - length
	 * 
	 * Pc = middle point on the screen = P0 + screenDistance * Vto
	 * Pij = target point on screen (middle of (i,j) pixel)
	 * Rx = screenWidth/Nx = width of one pixel
	 * Ry =  screenHeight/Ny = heigth of one pixel
	 * yi = (i - Ny/2)*Ry + Ry/2
	 * Xj = (j � Nx/2)*Rx + Rx/2
	 * if yi = 0 and xj = 0 then Pij = Pc.
	 * Pij = Pc + xj * vright - yi * vup.
	 * our ray = ray from P0 to Pij:
	 * Vij = vector toward (i,j) = Pij - P0
	 * ray = (P0, Vij)
	 * 
	 * @return ray from P0 to point(i,j) on the screen 
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
		if (Util.isZero(screenDistance))
		{
			throw new IllegalArgumentException("distance cannot be 0");
		}

		Point3D Pc = _p0.add(_vTo.scale(screenDistance));

		double Ry = screenHeight/nY;
		double Rx = screenWidth/nX;

		double yi =  ((i - nY/2d)*Ry + Ry/2);
		double xj=   ((j - nX/2d)*Rx + Rx/2);

		Point3D Pij = new Point3D(Pc);

		if (! Util.isZero(xj))
		{
			Pij = Pij.add(_vRight.scale(xj));
		}
		if (! Util.isZero(yi))
		{
			Pij = Pij.add(_vUp.scale((-1) * yi));
		}

		Vector Vij = Pij.subtract(_p0);

		return new Ray(_p0,Vij);

	}

	/**
	 * 
	 * @param nX nX num of pixels of X line
	 * @param nY num of pixels of Y line
	 * @param j last coordinate of target pixel
	 * @param i first coordinate of target pixel
	 * @param screenDistance distance between screen and camera
	 * @param screenWidth width of screen - length
	 * @param screenHeight height of screen - length
	 * 
	 * 
	 * @return arrayList of rays walks through specific pixel
	 */
	public ArrayList<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, int numOfRays)
	{
		if (Util.isZero(screenDistance))
		{
			throw new IllegalArgumentException("distance cannot be 0");
		}

		ArrayList<Ray> rays = new ArrayList<Ray>();
		
		//amount of mini-pixels
		int sqrt = (int)Math.sqrt(numOfRays);
		if (Math.sqrt(numOfRays) != sqrt) 
		{
			sqrt += 1;
		}
		
		//save in which pixel we have already sent rays.
		boolean[][] savedRays = new boolean[sqrt][sqrt];
		
		//initialization
		for (int k = 0; k < sqrt; k++) 
		{
			for (int k2 = 0; k2 < sqrt; k2++) 
			{
				savedRays[k][k2] = false;
			}
		}
		
		
		
		//center ray
		rays.add(constructRayThroughPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight));
		
		if (numOfRays == 0) 
		{
			return rays;
		}
		
		for (int k = 0; k < numOfRays; k++) 
		{
			Random random = new Random();
			int addToI = random.nextInt(sqrt);
			int addToJ = random.nextInt(sqrt);
			if (!savedRays[addToI][addToJ])
			{
				rays.add(constructRayThroughPixel(sqrt*nX,sqrt*nY, sqrt*j+addToJ, sqrt*i+addToI,screenDistance, screenWidth,screenHeight));
				savedRays[addToI][addToJ] = true;
			}
			else 
			{
				k--;
			}
		}
		return rays;
	}
	
	
	
}
