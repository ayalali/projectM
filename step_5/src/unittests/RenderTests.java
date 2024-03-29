package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author ayala and naama
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(Point3D.ZERO,new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(100);
        scene.set_background(new Color(75,127,90));
        scene.set_ambientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));
        
        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();//our image
        render.printGrid(50, java.awt.Color.YELLOW);//grid
        render.writeToImage();//into files
    }

}
