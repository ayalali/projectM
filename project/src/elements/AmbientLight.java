package elements;

import primitives.Color;

/**
 * Ambient light is a ambient light source represents a fixed-intensity and 
 * fixed-color light source that affects all objects in the scene equally.
 * 
 * @author ayala and naama
 *
 */
public class AmbientLight extends Light
{

	//constructors
	
	/**
	 * @param _intensity (Ia) the color we need to considered of the light in the scene
	 * @param _ka (Ka) 
	 * 
	 * Ip = Ia * Ka
	 */
	public AmbientLight(Color intensity, double ka) 
	{
		super(intensity.scale(ka));
	}
	

}
