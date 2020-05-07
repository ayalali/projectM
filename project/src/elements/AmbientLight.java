package elements;

import primitives.Color;

/**
 * Ambient light is a ambient light source represents a fixed-intensity and 
 * fixed-color light source that affects all objects in the scene equally.
 * 
 * @author ayala and naama
 *
 */
public class AmbientLight {

	/**
	 * _intensity (Ip) ambient light color
	 */
	private Color _intensity;

	/**
	 * @param _intensity (Ip) the color we need to considered of the light in the scene
	 */
	public AmbientLight(Color _intensity) {
		this._intensity = new Color(_intensity);
	}
	/**
	 * @param _intensity (Ia) the color we need to considered of the light in the scene
	 * @param _ka (Ka) 
	 * 
	 * Ip = Ia * Ka
	 */
	public AmbientLight(Color _intensity, double _ka) {
		this._intensity = new Color(_intensity.scale(_ka));
	}
	
	public Color get_intensity() {
		return new Color(_intensity);
	}

//    public java.awt.Color getIntensity() {
//        return _intensity.getColor();
//    }

}
