package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * represents spot light
 * 
 * @author ayala and naama
 *
 */
public class SpotLight extends PointLight 
{

	//fields
	
	/**
	 * light's direction
	 */
	private Vector _direction;

	//constructor
	
	/**
	 * Spot light constructor
	 * 
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this._direction = new Vector(direction);
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
