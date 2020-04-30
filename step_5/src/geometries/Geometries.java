
package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author ayala and naama
 *
 */
public class Geometries implements Intersectable {

	/**
	 * List of geometries
	 */
	private List<Intersectable> _geometries;
	
	
	/**
	 * default constructor
	 */
	public Geometries() {
		_geometries = new ArrayList<Intersectable>();
	}
	/**
	 * Geometries constructor
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		_geometries = new ArrayList<Intersectable>();
		add(geometries);
	}

	/**
	 * Adds geometry to the list
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable g : geometries) {
			_geometries.add(g);
		}
	}
	

	@Override
	public List<Point3D> findIntersections(Ray r) {
		List<Point3D> intersections = null;

		for (Intersectable g : _geometries) {
            List<Point3D> tempIntersections = g.findIntersections(r);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
	}

}
