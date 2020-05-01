/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Cylinder;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import org.junit.Test;

/**
 * 
 * Unit tests for geometries.Cylinder class
 * 
 * @author ayala and naama
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Vector n = null;
		Point3D p = new Point3D(6, 7, 8);
		Point3D p0 = new Point3D(1, 2, 3); 
		Vector v = new Vector(new Point3D(-5, 0, -2)).normalize();
		Ray ray = new Ray(p0, v);
		Cylinder c = new Cylinder(5,ray,7);
		double t = p.subtract(p0).dotProduct(v);
		Point3D o = null;
		o = p0.add(v.scale(t));
		n = p.subtract(o).normalize();
		assertEquals("ERROR: CylinderTests.getNormal() wrong value", n, c.getNormal(p));
	}

}
