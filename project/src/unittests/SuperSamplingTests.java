package unittests;

import org.junit.Test;
import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

public class SuperSamplingTests 
{
	@Test
	public void heart()
	{
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.5));

		scene.addGeometries(
				//floor
				new Triangle(new Color(java.awt.Color.BLACK), new Material(0.2, 0.2, 50, 0, 0.6),
                        new Point3D(-150, 27, 150)/*top point*/, new Point3D(-100, 180, 0), new Point3D(100, 180, 0)),
				new Triangle(new Color(java.awt.Color.BLACK), new Material(0.2, 0.2, 50, 0, 0.6),
                        new Point3D(150, 27, 150), new Point3D(-150, 27, 150), new Point3D(100, 180, 0)),
				//wall
				new Triangle(Color.BLACK, new Material(0.2, 0.2, 50, 0.6, 0),
                        new Point3D(-150, 27, 150), new Point3D(120, 27, 150), new Point3D(120, -120, 150)),
				new Triangle(Color.BLACK, new Material(0.2, 0.2, 50, 0.6, 0),
                      new Point3D(-150, 27, 150), new Point3D(-120, -120, 150), new Point3D(120, -120, 150))
				
				);
		scene.addLights(
				
				
				);
		
		ImageWriter imageWriter = new ImageWriter("heart", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
}
