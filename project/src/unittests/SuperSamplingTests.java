package unittests;

import org.junit.Test;
import elements.*;
import geometries.Plane;
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
		Scene scene = new Scene("Scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.5));

		scene.addGeometries(
				
				//floor
				new Plane(new Color(255, 204, 255), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-150, 150, 100)/*top point*/, new Point3D(-100, 210, 0), new Point3D(100, 210, 0)),
				
				//heart
				
				//1
				new Triangle(new Color(140, 26, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(25, -60, 130), new Point3D(0, -40, 130), new Point3D(11, -20, 125)),
				//2
				new Triangle(new Color(92, 0, 230), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(25, -60, 130), new Point3D(50, -60, 130), new Point3D(42, -52, 120)),
				//3
				new Triangle(new Color(57, 74, 225), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(50, -60, 130), new Point3D(65, -40, 130), new Point3D(42, -52, 120)),
				//4
				new Triangle(new Color(23, 28, 195), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(70, -20, 130), new Point3D(65, -40, 130), new Point3D(65, -14, 130)),
				//5
				new Triangle(new Color(16, 21, 168), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(70, -20, 130), new Point3D(60, 0, 130), new Point3D(65, -14, 130)),
				//6
				new Triangle(new Color(51, 51, 204), new Material(0.2, 0.4, 60, 0, 0),
	                     new Point3D(0, 60, 130), new Point3D(60, 0, 130), new Point3D(38, 25, 130)),
				//7
				new Triangle(new Color(102, 0, 255), new Material(0.2, 0.4, 40, 0, 0),
	                     new Point3D(11, -20, 125), new Point3D(25, -60, 130), new Point3D(42, -52, 120)),
				//8
				new Triangle(new Color(48, 64, 210), new Material(0.2, 0.4, 70, 0, 0),
	                     new Point3D(65, -14, 130), new Point3D(65, -40, 130), new Point3D(42, -52, 120)),
				//9
				new Triangle(new Color(117, 26, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(65, -14, 130), new Point3D(11, -20, 125), new Point3D(42, -52, 120)),
				//10
				new Triangle(new Color(115, 0, 230), new Material(0.2, 0.4, 30, 0, 0),
	                     new Point3D(11, -20, 125), new Point3D(60, 0, 130), new Point3D(65, -14, 130)),
				//11
				new Triangle(new Color(47, 17, 196), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(38, 20, 130), new Point3D(60, 0, 130), new Point3D(38, 25, 130)),
				//12
				new Triangle(new Color(133, 51, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(38, 20, 130), new Point3D(60, 0, 130), new Point3D(11,-20, 125)),
				//13
				new Triangle(new Color(255, 161, 212), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(0,-40, 130), new Point3D(-25,-60, 130), new Point3D(-35,-55, 130)),
				//14
				new Triangle(new Color(255, 128, 170), new Material(0.2, 0.4, 55, 0, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-25,-60, 130), new Point3D(-35,-55, 130)),
				//15
				new Triangle(new Color(255, 77, 136), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-65,-40, 130), new Point3D(-40,-30, 120)),
				//16
				new Triangle(new Color(230, 0, 76), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-65,-40, 130), new Point3D(-70,-20, 130), new Point3D(-61,-25, 130)),
				//17
				new Triangle(new Color(255, 0, 85), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-60,0, 130), new Point3D(-70,-20, 130), new Point3D(-61,-25, 130)),
				//18
				new Triangle(new Color(255, 102, 179), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-60, 0, 130), new Point3D(0, 60, 130), new Point3D(-32,25, 130)),
				//19
				new Triangle(new Color(245, 125, 169), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-40,-30, 120), new Point3D(-35,-55, 130)),
				//20
				new Triangle(new Color(255, 128, 213), new Material(0.2, 0.4, 60, 0, 0),
	                     new Point3D(0,-40, 130), new Point3D(-40,-30, 120), new Point3D(-35,-55, 130)),
				//21
				new Triangle(new Color(255, 51, 119), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-61,-25, 130), new Point3D(-65,-40, 130), new Point3D(-40,-30, 120)),
				//22
				new Triangle(new Color(255, 26, 102), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-61,-25, 130), new Point3D(-60,0, 130), new Point3D(-40,-30, 120)),
				//23
				new Triangle(new Color(255, 134, 179), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-32,25, 130), new Point3D(-60,0, 130), new Point3D(-40,-30, 120)),
				//24
				new Triangle(new Color(253, 128, 255), new Material(0.2, 0.4, 65, 0, 0),
	                     new Point3D(0,-40, 130), new Point3D(-40,-30, 120), new Point3D(11,-20, 125)),
				//25
				new Triangle(new Color(255, 128, 223), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-32,25, 130), new Point3D(-40,-30, 120), new Point3D(11,-20, 125)),
				//26
				new Triangle(new Color(224, 102, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-10,48, 130), new Point3D(0, 60, 130), new Point3D(-32,25, 130)),
				//27
				new Triangle(new Color(163, 102, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(0, 60, 130), new Point3D(38,25, 130), new Point3D(13,40, 120)),
				//28
				new Triangle(new Color(235, 68, 229), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(0, 60, 130), new Point3D(-10,48, 130), new Point3D(13,40, 120)),
				//29
				new Triangle(new Color(219, 77, 255), new Material(0.2, 0.4, 55, 0, 0),
	                     new Point3D(-32,25, 130), new Point3D(-10,48, 130), new Point3D(13,40, 120)),
				//30
				new Triangle(new Color(224, 102, 255), new Material(0.2, 0.4, 50, 0, 0),
	                     new Point3D(-32,25, 130), new Point3D(11,-20, 125), new Point3D(13,40, 120)),
				//31
				new Triangle(new Color(148, 77, 255), new Material(0.2, 0.4, 60, 0, 0),
	                     new Point3D(38,20, 130), new Point3D(38,25, 130), new Point3D(13,40, 120)),
				//32
				new Triangle(new Color(153, 51, 255), new Material(0.2, 0.4, 55, 0, 0),
	                     new Point3D(38,20, 130), new Point3D(11,-20, 125), new Point3D(13,40, 120))


				
				);
		scene.addLights(
				
				new DirectionalLight(new Color(java.awt.Color.pink), new Vector(0, 0.5, 1)),
				
				new SpotLight(new Color(255, 255, 222), new Point3D(0,-90 , 130), new Vector(0, 1,-1), 0, 0.5, 1),
				
				new PointLight(new Color(17, 255, 65), new Point3D(0, -65 , 119), 1, 0.0001, 0.0001)
				
				//new PointLight(new Color(248, 169, 153), new Point3D(-150, 119, 149), 1, 0.0005, 0.0005),
		
				);
		
		ImageWriter imageWriter = new ImageWriter("heart", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
}
