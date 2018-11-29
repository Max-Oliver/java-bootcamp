package Materiales_Estudio;


public class Cuadernos implements Materiales{

	//atributoss
	private int _id = 1;
	private String _Nombre = "Papiro 96";
	private int _Cant = 10;
	private int _Precio = 146;
	
	//propiedades
	public int getId() 
	{
		return _id;
	}
	public void setId(int ID) 
	{
		this._id = ID;	
	}
	
	public String getNombre()
	{
		return _Nombre;
	}
	public void setNombre(String Nom) 
	{
		this._Nombre = Nom;
	}
	
	public int Cant() 
	{
		return _Cant;
	}
	public void setCant(int pCant) 
	{
	 this._Cant = pCant;
	}
	
	public int Precio() 
	{
		return _Precio;
	}
	public void setPrecio(int pPrecio) 
	{
		this._Precio = pPrecio;
	}
	
	//Constructor vacio
	 public Cuadernos() {
		
	}
	
	 //Constructor Completo
	@Override
	public Materiales getMaterial() {
		Cuadernos cuad = new Cuadernos();
		cuad._id = _id;
		cuad._Nombre = _Nombre;
		cuad._Cant = _Cant;
		cuad._Precio = _Precio;
		return cuad;
	}
	
	@Override
	public String toString() {
		return "Cuadernos [_id=" + _id + ", _Nombre=" + _Nombre + ", _Cant=" + _Cant + ", _Precio=" + _Precio + "]";
	}

}