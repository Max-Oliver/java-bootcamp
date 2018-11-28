package Abstract2.Productos;

public class Calzado implements Producto{
	
	//Atributos
	private int 	_idProd;
	private String  _Nombre;
	private int 	_Costo;
	private int 	_Cant;
	
	//Este objeto instanciado no deberia estar aca. 
	//public Mochilas _unaMochi;
	
	/* A futuro rehacer las propiedades con restricciones*/
	
	//propiedades
	//Ejmplo de prop con restriccion y manejo de exceptciones
	public int get_idProd() throws Exception
	{
		if (_idProd != 0) 
		{
			return _idProd;
		} else 
		{
				throw new Exception("Error - No se encontro producto (0).");
		}		
	}
	public void set_idProd(int _idProd) 
	{
		this._idProd = _idProd;
	}
	public String get_Nombre() 
	{
		return _Nombre;
	}
	public void set_Nombre(String _Nombre) 
	{
		this._Nombre = _Nombre;
	}
	public int get_Costo() 
	{
		return _Costo;
	}
	public void set_Costo(int _Costo) 
	{
		this._Costo = _Costo;
	}
	
	public int get_Cant() 
	{
		return _Cant;
	}
	public void set_Cant(int _Cant)
	{
    this._Cant = _Cant;

	}
	
	@Override
	public void fill(int pIdProd, String pNom, int pCosto, int pCant) 
	{	
		this._idProd = pIdProd;
		this._Nombre = pNom;
		this._Costo = pCosto;
		this._Cant = pCant;			
	}
	
}
