/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * represents directional light - like Sun
 * 
 * @author ayala and naama
 *
 */
public class DirectionalLight extends Light implements LightSource 
{

	//fields
	
	/**
	 * light's direction
	 */
	private Vector _direction;

	
	//constructor
	
	/**
	 * @param intensity the intensity of the light
	 * @param direction light's direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this._direction = new Vector(direction).normalize();
	}
	
	
	//other functions
	
	/**
	 * @param p the point of the geometry
	 * @return (IL) new Color of the intensity of p
	 * 
	 * 
	 * IL = I0
	 * 
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return super.get_intensity();
	}
	
	/**
	 * @param p the point of the geometry
	 * @return the normalize vector from the light to p
	 */
	@Override
	public Vector getL(Point3D p) {
		return _direction.normalized();
	}


	/**
	 * @param point the point of the geometry
	 * @return the distance between the point and the directionalLight
	 */
	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}

}
