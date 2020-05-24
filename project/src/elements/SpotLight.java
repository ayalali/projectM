package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * 
 * represents spot light (such as a luxo lamp)
 * 
 * @author ayala and naama
 *
 */
public class SpotLight extends PointLight 
{

	//fields
	
	private Vector _direction;

	//constructor
	
	/**
	 * Spot light constructor
	 * 
	 * @param intensity the intensity of the light
	 * @param position light's position
	 * @param kC light's powerful definition
	 * @param kL light's powerful definition
	 * @param kQ light's powerful definition
	 * @param direction light's direction
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this._direction = new Vector(direction).normalize();
	}
	
	
	//other functions
	
	/**
	 * @param p the point of the geometry
	 * @return (IL) the intensity color of the point
	 * 
	 * 
	 * IL = I0*max(0,dir*l) / (Kc + Kl*d + Kq*d^2)
	 * 
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double dl = _direction.dotProduct(getL(p));

        if (Util.isZero(dl)) {
            return Color.BLACK;
        }
        double help = Math.max(0, dl);
        Color pointLightIntensity = super.getIntensity(p);

        return (pointLightIntensity.scale(help));
	}
	
	/**
	 * @param p the point of the geometry
	 * @return the normalize vector from the light to p
	 */
	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}
	
	/**
	 * @param point the point of the geometry
	 * @return the distance between the point and the spotLight
	 */
	@Override
	public double getDistance(Point3D point) {
		return super.getDistance(point);
	}


}
