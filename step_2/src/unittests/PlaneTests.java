/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Plane;
import primitives.Point3D;
import primitives.Vector;

import org.junit.Test;
import static primitives.Util.*;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author ayala
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
		assertEquals("ERROR: Plane.getNormal() wrong value", new Vector(new Point3D(1, 3, 0)).normalize(), plane.getNormal(new Point3D(1,2,3)).normalize());
		
	}

}
