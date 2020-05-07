/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import org.junit.Test;

/**
 * Unit tests for geometries.Triangle class
 * 
 * @author ayala and naama
 *
 */
public class TriangleTests {

	@Test
	public void normalTest() {
		
		// ============ Equivalence Partitions Tests ==============
		
		Point3D p1 = new Point3D(1, 2, 1);
		Point3D p2 = new Point3D(-3, 0, 5);
		Point3D p3 = new Point3D(2, -2, -7);
		Vector v1 = new Vector(p2.subtract(p1)); //p1->p2
		Vector v2 = new Vector(p3.subtract(p1)); //p1->p3
		Triangle triangle = new Triangle(p1, p2, p3);
		Vector v3 = new Vector(v1.crossProduct(v2)); //expected
		v3.normalize();
		assertEquals("ERROR: TriangleTests.getNormal() wrong value", v3, triangle.getNormal(p1));
		
	}
	
	@Test
    public void findIntersectionsTest() 
	{
		Triangle triangle = new Triangle(new Point3D(1,0,0), new Point3D(3,0,0), new Point3D(2,1,0));
		
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray starts before and crosses the triangle (1 point)
		
		List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(2, 0.5, 1),new Vector(new Point3D(0,0,-1))));
        assertEquals("uncorrect values",List.of(new Point3D(2,0.5,0)), result);
        
        // TC02: Ray does not crosses the triangle (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(0,1,-0.5),new Vector(new Point3D(-1,0,0))));
        assertNull("Ray does not crosses the triangle", result);
        
		// TC03: Ray intersects the plane but not the triangle (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(-3, 1, 1),new Vector(new Point3D(0,0,1))));
        assertNull("Ray does not crosses the triangle", result);
        
		// TC04: Ray starts after the triangle (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(2,0,1),new Vector(new Point3D(1,0,0))));
        assertNull("Ray does not crosses the triangle", result);

        // =============== Boundary Values Tests ==================

        // TC11: Ray starts at triangle and goes outside (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(1,0,0),new Vector(new Point3D(0,0,1))));
        assertNull("Ray does not crosses the triangle", result);
        
        // TC12: Ray starts at triangle and merging in triangle (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(2,0,0),new Vector(new Point3D(1,0,0))));
        assertNull("Ray does not crosses the triangle", result);
        
        // TC12: Ray's line is tangent to the triangle (0 points)
        
        result = triangle.findIntersections(new Ray(new Point3D(0.5,0,0),new Vector(new Point3D(1,0,0))));
        assertNull("Ray does not crosses the triangle", result);

		
	}
	
}

