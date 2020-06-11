package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
* Test rendering an image
*/
public class TeapotTest 
{
	private final Camera camera = new Camera(new Point3D(0, 0, -1000), //
	new Vector(0, 0, 1), new Vector(0, 1, 0));
	private final Scene scene = new Scene("Test scene");
	
	private static final Color color = new Color(200, 0, 0);
	private static final Material mat = new Material(0.5, 0.5, 60);

	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void teapot() 
	{
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
						new Point3D(0, 0, 50)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
				0.0004, 0.0000006));
		ImageWriter imageWriter = new ImageWriter("teapot", 200, 200, 800, 800);
		Render render = new Render(imageWriter, scene);
		render.setMultithreading(3).setDebugPrint();
		render.renderImage(); 
		render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();
	}
}
