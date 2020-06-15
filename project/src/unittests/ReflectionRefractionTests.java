/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author naama and ayala
 *
 */
public class ReflectionRefractionTests 
{

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
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

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
				new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
				   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	@Test
	public void picture()
	{
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries(
				//floor
				new Triangle(new Color(java.awt.Color.BLACK), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-150, 27, 150)/*top point*/, new Point3D(-100, 120, 0), new Point3D(100, 120, 0)),
				new Triangle(new Color(java.awt.Color.BLACK), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(150, 27, 150), new Point3D(-150, 27, 150), new Point3D(100, 120, 0)),
				//wall
				new Triangle(new Color(89, 24, 94), new Material(0.2, 0.2, 50, 0.6, 0),
                        new Point3D(-150, 27, 150), new Point3D(120, 27, 150), new Point3D(120, -120, 150)),
				new Triangle(new Color(89, 24, 94), new Material(0.2, 0.2, 50, 0.6, 0),
                      new Point3D(-150, 27, 150), new Point3D(-120, -120, 150), new Point3D(120, -120, 150)),
				
				//big ball
                new Sphere(new Color(36,24,111), new Material(0.2, 0.4, 10, 0.9, 0), 67, new Point3D(0, 73, 100)),
                
                //orange ball
                new Sphere(new Color(255, 100, 0), new Material(0.3, 0.3, 60, 0, 0), 7, new Point3D(-22, 38, 120)),
                
                //light blue ball
                new Sphere(new Color(84, 196, 159), new Material(0.2, 0.2, 5, 0, 0.3), 20, new Point3D(0, 48, 90)),
                
                //pink ball
                new Sphere(new Color(204, 0, 0), new Material(0.2, 0.2, 90, 0.5, 0), 12, new Point3D(-18, 70, 67)),
				
                //mirror ball
                new Sphere(new Color(0, 51, 204), new Material(0.2, 0.5, 90, 0, 0.9), 13, new Point3D(25, 32, 115)),
                
                //green ball
                new Sphere(new Color(0, 77, 0), new Material(0.5, 0.2, 70, 0.1, 0), 10, new Point3D(35, 62, 80)),
                
//                //*********** lamp
//                
//                //light
//                new Sphere(new Color(255, 255, 0), new Material(0.3, 0.3, 60, 0, 0), 5.5, new Point3D(70, -68, 130)),
//                
//                //body
//                new Triangle(new Color(0,0,0), new Material(0.2, 0.2, 50, 0, 0),
//                	new Point3D(76, -79, 140), new Point3D(82,-67 , 140), new Point3D(56, -61, 140)), 
//                new Triangle(new Color(0,0,0), new Material(0.2, 0.2, 50, 0, 0),
//                    new Point3D(54, -68, 120), new Point3D(78,-72 , 120), new Point3D(74, -81, 120)),
//                new Triangle(new Color(0,0,0), new Material(0.2, 0.2, 50, 0, 0),
//                        new Point3D(78,-72 , 120), new Point3D(82,-61, 120), new Point3D(59,-52, 120)),
//                new Triangle(new Color(0,0,0), new Material(0.2, 0.2, 50, 0, 0),
//                        new Point3D(78,-72 , 120), new Point3D(79,-71, 120), new Point3D(115,-115, 120)),
//                
                
                //*********** picture on the wall
                
                //home
				new Triangle(new Color(191, 128, 64), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-72, -48, 147), new Point3D(-72,-38 , 147), new Point3D(-62, -38, 147)),
                new Triangle(new Color(191, 128, 64), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-72, -48, 147)/*top point*/, new Point3D(-62, -48, 147), new Point3D(-62, -38, 147)),
                
                //roof
                new Triangle(new Color(204, 0, 0), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-77, -48, 147), new Point3D(-57,-48 , 147), new Point3D(-67, -58, 147)),
                
                //sun
                new Sphere(new Color(255, 255, 0), new Material(0, 0, 90, 0, 0), 5, new Point3D(-75, -68, 147)),
                
                //grass
                new Triangle(new Color(51, 153, 51), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-62, -38, 147), new Point3D(-57,-38 , 147), new Point3D(-59, -40, 147)),
                new Triangle(new Color(51, 153, 51), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-57,-38 , 147), new Point3D(-53, -38, 147), new Point3D(-55, -40, 147)),
                new Triangle(new Color(51, 153, 51), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-76, -38, 147), new Point3D(-72,-38 , 147), new Point3D(-74, -40, 147)),
                new Triangle(new Color(51, 153, 51), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-82,-38 , 147), new Point3D(-76, -38, 147), new Point3D(-79, -40, 147)),
                
                //brown rectangle
                new Triangle(new Color(119, 64, 56), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-85, -80, 149)/*top point*/, new Point3D(-85, -35, 149), new Point3D(-50, -35, 149)),
				new Triangle(new Color(119, 64, 56), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-85, -80, 149), new Point3D(-50,-80 , 149), new Point3D(-50, -35, 149)),
				
				//white rectangle
				new Triangle(new Color(255, 255, 255), new Material(0.2, 0.2, 50, 0, 0),
                        new Point3D(-82, -77, 148)/*top point*/, new Point3D(-82, -38, 148), new Point3D(-53, -38, 148)),
				new Triangle(new Color(255, 255, 255), new Material(0.2, 0.2, 50, 0, 0),
						new Point3D(-82, -77, 148), new Point3D(-53,-77 , 148), new Point3D(-53, -38, 148))
				
				);

		scene.addLights(
				new DirectionalLight(new Color(196, 255, 200), new Vector(1, 0.5, 1)),
				
				new SpotLight(new Color(196, 255, 200),new Point3D(70, -68, 130), new Vector(2,1,0.5), 1, 0.0005, 0.0005),
				
				//mini lights on the floor
				new PointLight(new Color(248, 169, 153), new Point3D(-150, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-108, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-66, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(-24, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(18, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(60, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(102, 26, 149), 1, 0.0005, 0.0005),
				new PointLight(new Color(248, 169, 153), new Point3D(144, 26, 149), 1, 0.0005, 0.0005)
				
				
				//new SpotLight(new Color(248, 169, 153), new Point3D(90, -120, 150), new Vector(-1,1,0), 1, 0.00005, 0.0000005)
				/*new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(-1,1,0)),
        		new PointLight(new Color(java.awt.Color.BLUE), new Point3D(-10, 20,10 ), 1.5, 0.0005, 0.00005),
        		,
        		new PointLight(new Color(java.awt.Color.MAGENTA), new Point3D(85, -70, 137), 1, 0.0000001, 0.00000001),
        		new SpotLight(new Color(java.awt.Color.MAGENTA), new Point3D(60, 50, 0), new Vector(0, 1, 1), 1, 4E-5, 2E-7)*/);

		ImageWriter imageWriter = new ImageWriter("picture", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
}
