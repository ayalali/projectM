package elements;

import primitives.Color;

public class AmbientLight {

	private Color _intensity;

	public AmbientLight(Color _intensity) {
		this._intensity = _intensity;
	}
	public AmbientLight(Color _intensity, double _ka) {
		this._intensity = _intensity;
	}
	
	public Color get_intensity() {
		return _intensity;
	}

//    public java.awt.Color getIntensity() {
//        return _intensity.getColor();
//    }

}
