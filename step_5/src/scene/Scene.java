package scene;

import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * @author ayala and naama
 * 
 * This class represents scene with geometries, light and more components.
 *
 */
public class Scene 
{
	
	
	//fields
	
	/**
	 * scene's name
	 */
	private String _name;
	/**
	 * scene's background
	 */
	private Color _background;
	/**
	 * scene's ambient light
	 */
	private AmbientLight _ambientLight;
	/**
	 * geometries in scene
	 */
	private Geometries _geometries;
	/**
	 * represents the camera of the scene
	 */
	private Camera _camera;
	/**
	 * distance between camera and scene's screen
	 */
	private double _distance;
	
	//constructor
	
	/**
	 * @param _name scene's name
	 * creates new geometries collection.
	 */
	public Scene(String name) {
		this._name = name;
		//_geometries = new Geometries();
	}
	
	
	//getters and setters
	
	/**
	 * @return scene's name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @return background color
	 */
	public Color get_background() {
		return new Color(_background);
	}
	/**
	 * @param _background background color
	 * update background color
	 */
	public void set_background(Color _background) {
		this._background = new Color(_background);
	}
	/**
	 * @return Ambient light of scene
	 */
	public AmbientLight get_ambientLight() {
		return new AmbientLight(_ambientLight.get_intensity());
	}
	/**
	 * @param _ambientLight Ambient light of scene
	 * update ambient light intensity
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = new AmbientLight(_ambientLight.get_intensity());
	}
	/**
	 * @return list of geometries the scene introduce
	 */
	public Geometries get_geometries() {
		return new Geometries(_geometries);
	}

	/**
	 * @return camera details
	 */
	public Camera get_camera() {
		return new Camera(_camera.getLocation(), _camera.getVright(), _camera.getVup());
	}
	/**
	 * @param _camera scene's camera
	 * update camera details
	 */
	public void set_camera(Camera camera) {
		this._camera = new Camera(camera.getLocation(), camera.getVright(), camera.getVup());
	}
	/**
	 * @return distance between camera and scene's screen
	 */
	public double get_distance() {
		return _distance;
	}
	/**
	 * @param _distance distance between camera and scene's screen
	 * update distance
	 */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}
	
	//other functions
	
	/**
	 * @param geometries collection of geometries
	 * 
	 * add geometries to scene's geometries
	 */
	public void addGeometries(Intersectable... geometries)
	{
		_geometries.add(geometries);
	}
	
}
