package Vestimenta;

public class Mochilas implements Producto {

	// Atributos
	private int _idProd;
	private String _Nombre;
	private int _Costo;
	private int _Cant;

	/*
	 * A futuro rehacer las propiedades con restricciones Ejmplo de prop con
	 * restriccion y manejo de exceptciones
	 */

	// propiedades
	public int get_idProd() throws Exception {
		if (_idProd != 0) {
			return _idProd;
		} else {
			throw new Exception("Error - No se econtro proeducto.");
		}
	}

	public void set_idProd(int _idProd) {
		this._idProd = _idProd;
	}

	public String get_Nombre() {
		return _Nombre;
	}

	public void set_Nombre(String _Nombre) {
		this._Nombre = _Nombre;
	}

	public int get_Costo() {
		return _Costo;
	}

	public void set_Costo(int _Costo) {
		this._Costo = _Costo;
	}

	public int get_Cant() {
		return _Cant;
	}

	public void set_Cant(int _Cant) {
		this._Cant = _Cant;
	}

	// Constructor vacio
	public Mochilas() {
	}

	// Constructor del objeto
	@Override
	public Producto getProducto() {
		Mochilas Mo = new Mochilas();
		Mo._idProd = _idProd;
		Mo._Nombre = _Nombre;
		Mo._Costo = _Costo;
		Mo._Cant = _Cant;
		return Mo;
	}

	@Override
	public String toString() {
		return "Mochilas [_idProd=" + _idProd + ", _Nombre=" + _Nombre + ", _Costo=" + _Costo + ", _Cant=" + _Cant
				+ "]";
	}



}
