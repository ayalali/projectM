/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;

/**
 * 
 * 
 * @author ayala and naama
 *
 */
public abstract class Light {

	
	protected Color _intensity;

	/**
	 * @return _intensity (Ip) color
	 */
	public Color get_intensity() {
		return _intensity;
	}

	/**
	 * Light constructor
	 * 
	 * @param _intensity (Ip) the color we need to considered of the light in the scene
	 */
	public Light(Color _intensity) {
		super();
		this._intensity = _intensity;
	}

	
}
