package Abstract2.Carrito;

public class Cuadernos implements Materiales{

	//atributoss
	private int _id;
	private String _Nombre;
	private int _Cant;
	private int _Precio;
	
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
	
	@Override
	public void draw(int pID ,String pNom ,int pCant, int pPrecio) 
	{
		this._id = pID;
		this._Nombre = pNom;
		this._Cant = pCant;
		this._Precio = pPrecio;
		
	}

}
