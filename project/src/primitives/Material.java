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
	
	/**
	 * transparency coefficient
	 */
	private double _kT;
	/**
	 * reflection coefficient
	 */
	private double _kR;
	/**
	 * diffuse coefficient
	 */
	private double _kD;
	/**
	 * specular coefficient
	 */
	private double _kS;
	/**
	 * Shininess coefficient
	 */
	private int _nShininess;

	
	//constructors
	
	/**
	 * @param kD
	 * @param kS
	 * @param nShininess
	 */
	public Material(double kD, double kS, int nShininess) 
	{
		this(0, 0, kD, kS, nShininess);
	}
	/**
	 * @param _kT
	 * @param _kR
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 */
	public Material(double _kT, double _kR, double _kD, double _kS, int _nShininess) {
		this._kT = _kT;
		this._kR = _kR;
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
	 * @return kT
	 */
	public double get_kT() {
		return _kT;
	}
	/**
	 * @return kR
	 */
	public double get_kR() {
		return _kR;
	}
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
