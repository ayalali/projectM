
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
	private List<Intersectable> geometriesList;
	
	
	/**
	 * default constructor
	 */
	public Geometries() {
		geometriesList = new ArrayList<Intersectable>();
	}
	/**
	 * Constructor
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		geometriesList = new ArrayList<Intersectable>();
		add(geometries);
	}

	/**
	 * Adds geometry to the list
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable g : geometries) {
			geometriesList.add(g);
		}
	}
	

	@Override
	public List<Point3D> findIntersections(Ray r) {
		List<Point3D> intersections = null;

		for (Intersectable g : geometriesList) {
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
