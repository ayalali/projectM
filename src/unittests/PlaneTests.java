/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

import java.util.List;

import org.junit.Test;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author ayala and naama
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		
		// ============ Equivalence Partitions Tests ==============
		
		Plane plane = new Plane(new Point3D(1,2,3), new Vector(new Point3D(1, 3, 0)));
		assertEquals("ERROR: PlaneTests.getNormal() wrong value", new Vector(new Point3D(1, 3, 0)).normalize(), plane.getNormal(new Point3D(1,2,3)).normalize());
	}

	@Test
    public void testFindIntersections() {

		Plane plane = new Plane (new Point3D(2,0,0),new Point3D(1,0,0),new Point3D(0,1,0));
		
        // ============ Equivalence Partitions Tests ==============
		
		 Point3D p = new Point3D(1, 0, 0);
		 List<Point3D> lst = List.of(p);

        // TC01: Ray intersects the plane (1 point)
		 List<Point3D> l = plane.findIntersections(new Ray(new Point3D(0,0,2),new Vector(0,0,-1)));
         assertEquals("Wrong number of points",1,l.size());
         
        // TC02: Ray does not intersect the plane (0 points)
        assertNull("Ray does not intersect the plane",plane.findIntersections(new Ray(new Point3D(0,0,2),new Vector(0,0,1))));
		
        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        
        // TC11: the ray included in the plane (0 points)
        assertNull("Ray included in the plane",plane.findIntersections(new Ray(new Point3D(1,0,0),new Vector(0,1,0))));
        
        // TC12: the ray not included in the plane (0 points)
        assertNull("Ray does not included in the plane",plane.findIntersections(new Ray(new Point3D(0,0,2),new Vector(0,0,1))));
        
        // **** Group: Ray is orthogonal to the plane (all tests 0 points)
        
        // TC13: according to P0 the ray before the plane(0 points)
        assertNull("Ray before the plane",plane.findIntersections(new Ray(new Point3D(0,0,-1),new Vector(0,0,-1))));

        // TC14: according to P0 the ray in the plane(0 points)
        assertNull("Ray starts in the plane",plane.findIntersections(new Ray(new Point3D(1,0,0),new Vector(0,0,-1))));

        // TC15: according to P0 the ray after the plane(0 points)
        assertNull("Ray after the plane",plane.findIntersections(new Ray(new Point3D(0,0,1),new Vector(0,0,1))));

        // **** Group: Ray is neither orthogonal nor parallel to the plane (all tests 0 points)
        
        // TC19: Ray begins at the plane (P0 is in the plane, but not the ray)
        assertNull("Ray starts in the plane",plane.findIntersections(new Ray(new Point3D(4,1,0),new Vector(0,1,1))));
        
        // TC20: Ray begins in the same point which appears as reference point in the plane (Q)
        assertNull("Ray starts in the plane",plane.findIntersections(new Ray(new Point3D(1,0,0),new Vector(1,1,0))));
	}
}
