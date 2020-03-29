/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static java.lang.System.out;
import static primitives.Util.isZero;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Tube class
 * 
 * @author ayala
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		// ============ Equivalence Partitions Tests ==============
		
		Vector n = null;
		Point3D p = new Point3D(6, 7, 8);
		Point3D p0 = new Point3D(1, 2, 3); //Tube Ray's point
		Vector v = new Vector(new Point3D(-5, 0, -2)).normalize();//Tube Ray's vector
		Ray ray = new Ray(p0, v);
		Tube tube = new Tube(5, ray);
    	//t = v (P – P0)
    	double t = p.subtract(p0).dotProduct(v);
    	// O = P0 + tv.
    	Point3D o = null;
    	o = p0.add(v.scale(t));
    	//n = p - o
    	n = p.subtract(o).normalize();
    	assertEquals("ERROR: TubeTests.getNormal() wrong value", n, tube.getNormal(p));
	}
}
