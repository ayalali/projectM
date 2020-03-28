/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

import org.junit.Test;

/**
 * Unit tests for geometries.Sphere class
 * 
 * @author ayala
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		// ============ Equivalence Partitions Tests ==============
		
		Point3D p = new Point3D(1, 0, -5);
		Point3D otherP = new Point3D(4, 3, -2);
		Sphere sphere = new Sphere(3, p);
		Vector vector = new Vector(otherP.subtract(p)).normalize();
		assertEquals("ERROR: Sphere.getNormal() wrong value", vector, sphere.getNormal(otherP));
	}

}
