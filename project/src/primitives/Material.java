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
	
	private double _kT;
	private double _kR;
	private double _kD;
	private double _kS;
	private int _nShininess;

	
	//constructors
	
	/**
	 * @param kD diffuse coefficient
	 * @param kS specular coefficient
	 * @param nShininess Shininess coefficient
	 */
	public Material(double kD, double kS, int nShininess) 
	{
		this(kD, kS, nShininess, 0, 0);
	}
	/**
	 * @param _kT transparency coefficient
	 * @param _kR reflection coefficient
	 * @param _kD diffuse coefficient
	 * @param _kS specular coefficient
	 * @param _nShininess Shininess coefficient
	 */
	public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) 
	{
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
		this(m._kD, m._kS, m._nShininess, m._kT, m._kR);
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
