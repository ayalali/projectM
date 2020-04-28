package unittests;


import primitives.*;
import geometries.*;
import elements.Camera;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static primitives.Util.*;

import org.junit.Test;

/**
 * 
 * @author ayala and naama
 *
 */
public class IntegrationTests 
{
	
	@Test
	public void WithSphereTest() 
	{
		Camera cam1 = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, -1, 0));
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
	    assertEquals("amount of intersections is wrong",2,count);
	    System.out.println("count: " + count);
	    
	    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(1, 0, 0), new Vector(0, -1, 0));
        sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));

        results = null;
        count = 0;
        
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

        assertEquals("amount of intersections is wrong",18,count);
        System.out.println("count: "+count);
        
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(1, 0, 0), new Vector(0, -1, 0));
        sph =  new Sphere(2, new Point3D(0, 0, 2));

        results = null;
        count = 0;
        
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

        assertEquals("amount of intersections is wrong",10,count);
        System.out.println("count: "+count);
        
        cam = new Camera(new Point3D(0, 0, -0.5), new Vector(1, 0, 0), new Vector(0, -1, 0));
        sph =  new Sphere(4, new Point3D(0, 0, 1));

        results = null;
        count = 0;
        
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

        assertEquals("amount of intersections is wrong",9,count);
        System.out.println("count: "+count);
        
        cam = new Camera(new Point3D(0, 0, -0.25), new Vector(1, 0, 0), new Vector(0, -1, 0));
        sph =  new Sphere(0.5, new Point3D(0, 0, -1));

        results = null;
        count = 0;
        
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

        assertEquals("amount of intersections is wrong",0,count);
        System.out.println("count: "+count);
	}
	 

	@Test
	public void withPlaneTest() {
		List<Point3D> points;
		int count = 0;
		
		Camera camera = new Camera(new Point3D(0,0,-1), new Vector(1,0,0), new Vector(0,-1,0));
		
		Plane plane = new Plane(new Point3D(0,0,2), new Vector(camera.getVtoward()));
		
		int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                points = plane.findIntersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (points != null)
                    count += points.size();
            }
        }
        assertEquals("amount of intersections is wrong",9, count);
        
        points = null;
		count = 0;
		
		plane = new Plane(new Point3D(0,0,2), camera.getVright().crossProduct(new Vector(0,-2,-1)));
		
		// TODO explanations
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                points = plane.findIntersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (points != null)
                    count += points.size();
            }
        }
        assertEquals("amount of intersections is wrong", 9, count);
        
        points = null;
		count = 0;
		
		plane = new Plane(new Point3D(0,0,2), camera.getVright().crossProduct(new Vector(0,-0.5,-1)));
		
		// TODO explanations
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                points = plane.findIntersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (points != null)
                    count += points.size();
            }
        }
        assertEquals("amount of intersections is wrong",6, count);
	}
	
	@Test
	public void withTriangleTest() 
	{
		int count = 0;
		List<Point3D> points = new ArrayList<Point3D>();
		Camera camera = new Camera(new Point3D(0,0,-1), new Vector(1,0,0), new Vector(0,-1,0));
	
		Triangle triangle1 = new  Triangle(new Point3D(0,-1,2),
				new Point3D(1,1,2), new Point3D(-1,1,2));
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				points = (triangle1.findIntersections
						(camera.constructRayThroughPixel
								(3, 3, j, i, 1, 3, 3))); 
				if (points != null)
				{
					count += points.size();
				}
			}
		}
		assertEquals("amount of intersections is wrong",1, count);
		
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
				if (points != null)
				{
					count += points.size();
				}
			}
		}
		assertEquals("amount of intersections is wrong",2, count);
	}
}