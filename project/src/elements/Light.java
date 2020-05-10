/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;

/**
 * represents light
 * 
 * @author ayala and naama
 *
 */
public abstract class Light {

	//fields
	
	/**
	 * color light intensity
	 */
	protected Color _intensity;

	
	//getters
	
	/**
	 * @return _intensity (Ip) color
	 */
	public Color get_intensity() {
		return _intensity;
	}

	
	//constructor
	
	/**
	 * Light constructor
	 * 
	 * @param _intensity (Ip) the color we need to considered of the light in the scene
	 */
	public Light(Color _intensity) {
		this._intensity = _intensity;
	}

	
}
