/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity (Shape)
 * 
 * 
 * @author ayala and naama
 *
 */
public interface Intersectable 
{
	/**
	 * @param r 
	 * @return List of GeoPoints that the ray hit on
	 */
	public List<GeoPoint> findIntersections(Ray r);
	
	
	/**
	 * The class GeoPoint adds to every point in which geometry she's in.
	 *
	 */
	public static class GeoPoint {
	    
		public Geometry _geometry;
	    
		public Point3D _point;
		
	    /**
		 * @param geometry in which geometry the point in.
		 * @param point the point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this._geometry = geometry;
			this._point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (_geometry == null) {
				if (other._geometry != null)
					return false;
			} else if (!_geometry.equals(other._geometry))
				return false;
			if (_point == null) {
				if (other._point != null)
					return false;
			} else if (!_point.equals(other._point))
				return false;
			return true;
		}
	    
	    
	}

}
