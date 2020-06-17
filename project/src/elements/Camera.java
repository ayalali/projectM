package elements;

import java.awt.List;
import java.util.ArrayList;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
	 * Xj = (j – Nx/2)*Rx + Rx/2
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

		Point3D Pc = new Point3D(_p0.add(_vTo.scale(screenDistance)));

		double Ry = screenHeight/nY;
		double Rx = screenWidth/nX;

		double yi =  ((i - nY/2d)*Ry + Ry/2);
		double xj=   ((j - nX/2d)*Rx + Rx/2);

		Point3D Pij = new Point3D(Pc);

		if (! Util.isZero(xj))
		{
			Pij = new Point3D(Pij.add(_vRight.scale(xj)));
		}
		if (! Util.isZero(yi))
		{
			Pij = new Point3D(Pij.add(_vUp.scale((-1) * yi)));
		}

		Vector Vij = new Vector(Pij.subtract(_p0));

		return new Ray(_p0,Vij);

	}

	public ArrayList<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, int numOfRays)
	{
		if (Util.isZero(screenDistance))
		{
			throw new IllegalArgumentException("distance cannot be 0");
		}

		ArrayList<Ray> rays = new ArrayList<Ray>();
		int Kdown = 0;
		double Kup = 0;
		double length = 1.01;
		
		rays.add(constructRayThroughPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight));
		
		if (numOfRays == 0) 
		{
			return rays;
		}
		
		//view plane's center
		Point3D Pc = new Point3D(_p0.add(_vTo.scale(screenDistance)));

		double Ry = screenHeight/nY;
		double Rx = screenWidth/nX;
		double diagonal = Math.sqrt(Rx*Rx + Ry*Ry);

		if (numOfRays >= 8)
		{
			Kup = numOfRays / 4;
			Kdown = numOfRays / 4;
			if (Kup > Kdown)
			{
				Kup = Kdown + 1;
			}

		}
		else 
		{
			Kup = numOfRays / 2;
			Kdown = numOfRays / 2;
			if (Kup > Kdown) 
			{
				Kup = Kdown + 1;
			}
		}

		//up left corner
		for (double k = diagonal - 1.01; k <= 1.01; k -= diagonal-2.02/Kdown-1) 
		{
			double yi =  ((i - nY/2d)*Ry + Ry/k);
			double xj=   ((j - nX/2d)*Rx + Rx/k);
			
			Point3D Pij = new Point3D(Pc);

			if (! Util.isZero(xj))
			{
				Pij = new Point3D(Pij.add(_vRight.scale(xj)));
			}
			if (! Util.isZero(yi))
			{
				Pij = new Point3D(Pij.add(_vUp.scale((-1) * yi)));
			}

			Vector Vij = new Vector(Pij.subtract(_p0));
			
			rays.add(new Ray(Pij, Vij));
		}

		//up right corner
		for (double k = diagonal - 1.01, w = 1.01; k <= 1.01; k -= diagonal-2.02/Kdown-1, w+=diagonal-2.02/Kdown-1) 
		{
			double yi =  ((i - nY/2d)*Ry + Ry/w);
			double xj=   ((j - nX/2d)*Rx + Rx/k);
			
			Point3D Pij = new Point3D(Pc);

			if (! Util.isZero(xj))
			{
				Pij = new Point3D(Pij.add(_vRight.scale(xj)));
			}
			if (! Util.isZero(yi))
			{
				Pij = new Point3D(Pij.add(_vUp.scale((-1) * yi)));
			}

			Vector Vij = new Vector(Pij.subtract(_p0));
			
			rays.add(new Ray(Pij, Vij));
		}

		//left to right
		for (double k = Rx - 1.01; k <= 1.01 && (numOfRays >= 8); k -= Rx-2.02/Kdown-1) 
		{
			double yi =  ((i - nY/2d)*Ry + Ry/2);
			double xj=   ((j - nX/2d)*Rx + Rx/k);
			
			Point3D Pij = new Point3D(Pc);

			if (! Util.isZero(xj))
			{
				Pij = new Point3D(Pij.add(_vRight.scale(xj)));
			}
			if (! Util.isZero(yi))
			{
				Pij = new Point3D(Pij.add(_vUp.scale((-1) * yi)));
			}

			Vector Vij = new Vector(Pij.subtract(_p0));
			
			rays.add(new Ray(Pij, Vij));
		}

		//up to down
		for (double k = Rx - 1.01; k <= 1.01 && (numOfRays >= 8); k -= Rx-2.02/Kdown-1) 
		{
			double yi =  ((i - nY/2d)*Ry + Ry/k);
			double xj=   ((j - nX/2d)*Rx + Rx/2);
			
			Point3D Pij = new Point3D(Pc);

			if (! Util.isZero(xj))
			{
				Pij = new Point3D(Pij.add(_vRight.scale(xj)));
			}
			if (! Util.isZero(yi))
			{
				Pij = new Point3D(Pij.add(_vUp.scale((-1) * yi)));
			}

			Vector Vij = new Vector(Pij.subtract(_p0));
			
			rays.add(new Ray(Pij, Vij));
		}


		

		

		return rays;
	}
}
