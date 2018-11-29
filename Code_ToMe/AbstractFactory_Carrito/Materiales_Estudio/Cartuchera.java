package Materiales_Estudio;

public class Cartuchera implements Materiales {

	// atributoss // datos de prueba
	private int _id = 1;
	private String _Nombre = "BluLise";
	private int _Cant = 10;
	private int _Precio = 150;

	// propiedades
	public int getId() {
		return _id;
	}

	public void setId(int ID) {
		this._id = ID;
	}

	public String getNombre() {
		return _Nombre;
	}

	public void setNombre(String Nom) {
		this._Nombre = Nom;
	}

	public int getCant() {
		return _Cant;
	}

	public void setCant(int pCant) {
		this._Cant = pCant;
	}

	public int getPrecio() {
		return _Precio;
	}

	public void setPrecio(int pPrecio) {
		this._Precio = pPrecio;
	}

	//Constructor  vacio
	public Cartuchera() {}
	
	//Completo
	@Override
	public Materiales getMaterial() {
		Cartuchera cart = new Cartuchera();
		
		cart._id = _id;
		cart._Nombre = _Nombre;
		cart._Cant = _Cant;
		cart._Precio = _Precio;
		
		return cart;
	}

	@Override
	public String toString() {
		return "Cartuchera [_id=" + _id + ", _Nombre=" + _Nombre + ", _Cant=" + _Cant + ", _Precio=" + _Precio + "]";
	}
}
