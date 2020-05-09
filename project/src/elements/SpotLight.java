package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author ayala and naama
 *
 */
public class SpotLight extends PointLight {

	private Vector _direction;

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
	public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) {
		super(intensity, position, kC, kL, kQ);
		this._direction = new Vector(direction);
	}
	
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
