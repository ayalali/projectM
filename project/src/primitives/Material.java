package primitives;

/**
 * 
 * represents different materials that geometries are made from.
 * 
 * @author ayala and naama
 *
 */
public class Material 
{
	//fields
	
	private double _kD;
	private double _kS;
	private int _nShininess;

	
	//constructors
	
	/**
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 */
	public Material(double _kD, double _kS, int _nShininess) 
	{
		this._kD = _kD;
		this._kS = _kS;
		this._nShininess = _nShininess;
	}
	/**
	 * @param m material
	 * 
	 * copy constructor
	 */
	public Material(Material m)
	{
		this(m._kD, m._kS, m._nShininess);
	}

	//getters
	
	/**
	 * @return kD
	 */
	public double get_kD() 
	{
		return _kD;
	}
	/**
	 * @return kS
	 */
	public double get_kS() 
	{
		return _kS;
	}
	/**
	 * @return nShininess
	 */
	public int get_nShininess() 
	{
		return _nShininess;
	}
	
	
	
}
