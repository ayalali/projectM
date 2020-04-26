package unittests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import primitives.*;
import geometries.*;
import elements.Camera;
import java.util.List;

/**
 * 
 * 
 * @author ayala and naama
 *
 */
public class IntegrationTests 
{
	
	@Test
	void WithSphere1Test() 
	{
		Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
	    //TO DO
	    Sphere sph =  new Sphere(1, new Point3D(0, 0, 3));
	    //Ray ray = cam1.constructRayThroughPixel(3,3,0,0,1,3,3);
	    //List<Point3D> results =  sph.findIntersections(ray);
	    List<Point3D> results;
	    int count = 0;
	    int Nx = 3;
	    int Ny = 3;
	    for (int i = 0; i < 3; ++i) 
	    {
	        for (int j = 0; j < 3; ++j) 
	        {
	            results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	            if (results != null)
	                count += results.size();
	        }
	    }
	    assertEquals(2,count,"too bad");
	    System.out.println("count: " + count);
	}
	 
	    @Test
	    void WithSphere2Test() 
	    {
	    
	        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
	        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));

	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) 
	        {
	            for (int j = 0; j < 3; ++j) 
	            {
	                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals(18,count,"too bad");
	        System.out.println("count: "+count);
	    }
	
	@Test
	public void withPlaneTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void withTriangleTest() 
	{
		double count = 0;
		List<Point3D> points;
		Camera camera = new Camera(new Point3D(0,0,-1), new Vector(1,0,0), new Vector(0,-1,0));
	
		Triangle triangle1 = new  Triangle(new Point3D(0,-1,2),
				new Point3D(1,1,2), new Point3D(-1,1,2));
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				points = triangle1.findIntersections
						(camera.constructRayThroughPixel
								(3, 3, j, i, 1, 3, 3));
				count += points.size();
			}
		}
		assertEquals(1, count, "amount of intersections is wrong");
		
		count = 0;
		Triangle triangle2 = new  Triangle(new Point3D(0,-20, 2),
				new Point3D(1,1,2), new Point3D(-1,1,2));
		
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				points = triangle2.findIntersections
						(camera.constructRayThroughPixel
								(3, 3, j, i, 1, 3, 3));
				count += points.size();
			}
		}
		assertEquals(2, count, "amount of intersections is wrong");
	}

}
