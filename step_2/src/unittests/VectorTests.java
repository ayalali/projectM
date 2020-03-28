/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import primitives.Vector;
import org.junit.Test;
import static primitives.Util.*;

/**
 * 
 * Unit tests for primitives.Vector class
 * 
 * @author ayala
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     assertTrue("ERROR: subtruct() for equals vectors is not zero", v1.subtract(v1).lengthSquared() != 0);
	     assertTrue("ERROR: subtruct() wrong value", v1.subtract(v2).lengthSquared() != 126);
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector(0, 3, -2);
	     assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", !isZero(v1.dotProduct(v3)));
	     assertTrue("ERROR: dotProduct() wrong value", !isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, 4);
        assertTrue("ERROR: lengthSquared() wrong value", !isZero(v1.lengthSquared() - 14));
        assertTrue("ERROR: lengthSquared() wrong value", !isZero(v2.lengthSquared() - 25));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, 4);
        assertTrue("ERROR: lengthSquared() wrong value", !isZero(v1.length() - Math.sqrt(14)));
        assertTrue("ERROR: lengthSquared() wrong value", !isZero(v2.length() - Math.sqrt(25)));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		fail("Not yet implemented");
	}

}
