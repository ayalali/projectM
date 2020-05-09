/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author ayala and naama
 *
 */
public class DirectionalLight extends Light implements LightSource {

	private Vector _direction;

	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
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
