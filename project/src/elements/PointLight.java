package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource 
{
 
	//fields
	
	/**
	 * light's position
	 */
	protected Point3D _position;
	/**
	 * light's powerful definitions
	 */
	protected double _kC, _kL, _kQ;
	
	
	//constructor
	
	/**
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		this._position = new Point3D(position);
		this._kC = kC;
		this._kL = kL;
		this._kQ = kQ;
	}
	
	
	//other functions
	
	/**
	 * @param p
	 * @return
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return null;
	}
	
	/**
	 * @param p
	 * @return
	 */
	@Override
	public Vector getL(Point3D p) {
		return null;
	}

}
