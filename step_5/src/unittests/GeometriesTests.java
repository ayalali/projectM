package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

public class GeometriesTests {

	@Test
	public void findIntersectionsTest() {
		Plane p1 = new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(2,0,0));
		Plane p2 = new Plane(new Point3D(0,0,1),new Point3D(0,1,0),new Point3D(2,0,0));
		Geometries g = new Geometries(p1,p2);
		
		// ============ Equivalence Partitions Tests ==============

		// TC01: The ray crossed some of the geometries but not them all
        assertEquals("Ray crossed some of the geometries but not them all",1,g.findIntersections(new Ray(new Point3D(0,0,0.5), new Vector(0,0,1))).size());

        // =============== Boundary Values Tests ==================

        // TC02: There are no geometries
		assertNull("Geometry list is empty",new Geometries().findIntersections(new Ray(new Point3D(0,0,0.5), new Vector(0,0,1))));
		
        // TC03: The ray doesn't cross any geometry
        assertNull("Ray doesn't cross any geometry",g.findIntersections(new Ray(new Point3D(0,0,1.5), new Vector(0,1,1))));
		
		// TC04: the ray cross one geometry
        assertEquals("Ray crossed one geometry",1,g.findIntersections(new Ray(new Point3D(0,0,0.5), new Vector(0,1,-1))).size());
		
		// TC05: The ray cross all the geometries
        g.add(new Plane(new Point3D(0,1,0.5),new Point3D(0,1,0),new Point3D(2,0,0)));
        assertEquals("Ray crossed all the geometries",3,g.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(0,1,1))).size());
	}

}
