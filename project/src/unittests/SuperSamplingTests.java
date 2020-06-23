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
		Scene scene = new Scene("Scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.5));

		scene.addGeometries(
				//floor
				new Triangle(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-150, 120, 100)/*top point*/, new Point3D(-100, 180, 0), new Point3D(100, 180, 0)),
				new Triangle(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(150, 120, 100), new Point3D(-150, 120, 100), new Point3D(100, 180, 0)),
				//wall
				new Triangle(Color.BLACK, new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-150, 27, 150), new Point3D(120, 27, 150), new Point3D(120, -120, 150)),
				new Triangle(Color.BLACK, new Material(0.2, 0.2, 50, 0, 0),
                      new Point3D(-150, 27, 150), new Point3D(-120, -120, 150), new Point3D(120, -120, 150)),
				
				//heart
				
				//1
				new Triangle(new Color(204, 51, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(25, -60, 130), new Point3D(0, -40, 130), new Point3D(11, -20, 125)),
				//2
				new Triangle(new Color(255, 153, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(25, -60, 130), new Point3D(50, -60, 130), new Point3D(42, -52, 120)),
				//3
				new Triangle(new Color(255, 0, 102), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(50, -60, 130), new Point3D(65, -40, 130), new Point3D(42, -52, 120)),
				//4
				new Triangle(new Color(0, 153, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(70, -20, 130), new Point3D(65, -40, 130), new Point3D(65, -14, 130)),
				//5
				new Triangle(new Color(204, 0, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(70, -20, 130), new Point3D(60, 0, 130), new Point3D(65, -14, 130)),
				//6
				new Triangle(new Color(204, 204, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0, 60, 130), new Point3D(60, 0, 130), new Point3D(38, 25, 130)),
				//7
				new Triangle(new Color(0, 153, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(11, -20, 125), new Point3D(25, -60, 130), new Point3D(42, -52, 120)),
				//8
				new Triangle(new Color(102, 0, 51), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(65, -14, 130), new Point3D(65, -40, 130), new Point3D(42, -52, 120)),
				//9
				new Triangle(new Color(153, 0, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(65, -14, 130), new Point3D(11, -20, 125), new Point3D(42, -52, 120)),
				//10
				new Triangle(new Color(51, 51, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(11, -20, 125), new Point3D(60, 0, 130), new Point3D(65, -14, 130)),
				//11
				new Triangle(new Color(204, 0, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(38, 20, 130), new Point3D(60, 0, 130), new Point3D(38, 25, 130)),
				//12
				new Triangle(new Color(51, 153, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(38, 20, 130), new Point3D(60, 0, 130), new Point3D(11,-20, 125)),
				//13
				new Triangle(new Color(51, 51, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0,-40, 130), new Point3D(-25,-60, 130), new Point3D(-35,-55, 130)),
				//14
				new Triangle(new Color(153, 0, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-25,-60, 130), new Point3D(-35,-55, 130)),
				//15
				new Triangle(new Color(102, 102, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-65,-40, 130), new Point3D(-40,-30, 120)),
				//16
				new Triangle(new Color(204, 51, 153255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-65,-40, 130), new Point3D(-70,-20, 130), new Point3D(-61,-25, 130)),
				//17
				new Triangle(new Color(0, 102, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-60,0, 130), new Point3D(-70,-20, 130), new Point3D(-61,-25, 130)),
				//18
				new Triangle(new Color(102, 0, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-60, 0, 130), new Point3D(0, 60, 130), new Point3D(-32,25, 130)),
				//19
				new Triangle(new Color(0, 102, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-50,-60, 130), new Point3D(-40,-30, 120), new Point3D(-35,-55, 130)),
				//20
				new Triangle(new Color(153, 0, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0,-40, 130), new Point3D(-40,-30, 120), new Point3D(-35,-55, 130)),
				//21
				new Triangle(new Color(153, 51, 10253), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-61,-25, 130), new Point3D(-65,-40, 130), new Point3D(-40,-30, 120)),
				//22
				new Triangle(new Color(0, 38, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-61,-25, 130), new Point3D(-60,0, 130), new Point3D(-40,-30, 120)),
				//23
				new Triangle(new Color(255, 153, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-32,25, 130), new Point3D(-60,0, 130), new Point3D(-40,-30, 120)),
				//24
				new Triangle(new Color(102, 153, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0,-40, 130), new Point3D(-40,-30, 120), new Point3D(11,-20, 125)),
				//25
				new Triangle(new Color(204, 0, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-32,25, 130), new Point3D(-40,-30, 120), new Point3D(11,-20, 125)),
				//26
				new Triangle(new Color(102, 0, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-10,48, 130), new Point3D(0, 60, 130), new Point3D(-32,25, 130)),
				//27
				new Triangle(new Color(255, 51, 204), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0, 60, 130), new Point3D(38,25, 130), new Point3D(13,40, 120)),
				//28
				new Triangle(new Color(102, 0, 102), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(0, 60, 130), new Point3D(-10,48, 130), new Point3D(13,40, 120)),
				//29
				new Triangle(new Color(0, 0, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-32,25, 130), new Point3D(-10,48, 130), new Point3D(13,40, 120)),
				//30
				new Triangle(new Color(102, 179, 255), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(-32,25, 130), new Point3D(11,-20, 125), new Point3D(13,40, 120)),
				//31
				new Triangle(new Color(51, 0, 128), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(38,20, 130), new Point3D(38,25, 130), new Point3D(13,40, 120)),
				//32
				new Triangle(new Color(204, 0, 153), new Material(0.2, 0.2, 50, 0.6, 0),
	                     new Point3D(38,20, 130), new Point3D(11,-20, 125), new Point3D(13,40, 120))


				
				);
		scene.addLights(
				
				new DirectionalLight(new Color(java.awt.Color.pink), new Vector(0, 0.5, 1)),
				
				//new SpotLight(new Color(255, 255, 222), new Point3D(0,-90 , 130), new Vector(0, 1,-1), 0, 0.5, 1)
				
				new PointLight(new Color(java.awt.Color.BLUE), new Point3D(11,-20 , 125), 1, 0.0000001, 0.00000001),
				
				
				new PointLight(new Color(248, 169, 153), new Point3D(-150, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-108, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-66, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-24, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(18, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(60, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(102, 119, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(144, 119, 149), 1, 0.0005, 0.0005)
				);
		
		ImageWriter imageWriter = new ImageWriter("heart", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
}
