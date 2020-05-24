package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * represents point light  (such as a bulb)
 * 
 * @author ayala and naama
 */
public class PointLight extends Light implements LightSource 
{
 
	//fields
	
	protected Point3D _position;

	protected double _kC, _kL, _kQ;
	
	
	//constructor
	
	/**
	 * @param intensity the intensity of the light
	 * @param position light's position
	 * @param kC light's powerful definition
	 * @param kL light's powerful definition
	 * @param kQ light's powerful definition
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
	 * @param p the point of the geometry
	 * @return (IL) the intensity color of the point
	 * 
	 * 
	 * IL = I0 / (Kc + Kl*d + Kq*d^2)
	 * 
	 */
	@Override
	public Color getIntensity(Point3D p) {
		 double dsquared = p.distanceSquared(_position);
	     double d = p.distance(_position);

	     return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
	}
	
	/**
	 * @param p the point of the geometry
	 * @return the normalize vector from the light to p
	 */
	@Override
	public Vector getL(Point3D p) {
		if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
	}


	/**
	 * @param point the point of the geometry
	 * @return the distance between the point and the pointLight
	 */
	@Override
	public double getDistance(Point3D point) {
		return point.distance(_position);
	}

}
