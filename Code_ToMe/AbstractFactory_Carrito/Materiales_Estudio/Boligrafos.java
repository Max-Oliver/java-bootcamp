package Materiales_Estudio;

public class Boligrafos implements Materiales{

	// atributoss
	private int _id = 1;
	private String _Nombre = "Bic";
	private int _Cant = 50;
	private int _Precio = 15;

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

	public int Cant() {
		return _Cant;
	}

	public void setCant(int pCant) {
		this._Cant = pCant;
	}

	public int Precio() {
		return _Precio;
	}

	public void setPrecio(int pPrecio) {
		this._Precio = pPrecio;
	}

	// Constructor Vacio
	public Boligrafos() {
	}

	// Constructor Completo
	@Override
	public Materiales getMaterial() {
		Boligrafos bol = new Boligrafos();
		bol._id = _id;
		bol._Nombre = _Nombre;
		bol._Cant = _Cant;
		bol._Precio = _Precio;
		return bol;
	}

	@Override
	public String toString() {
		return "Boligrafos [_id=" + _id + ", _Nombre=" + _Nombre + ", _Cant=" + _Cant + ", _Precio=" + _Precio + "]";
	}

	

}
