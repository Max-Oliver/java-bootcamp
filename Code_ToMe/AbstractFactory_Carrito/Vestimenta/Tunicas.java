package Vestimenta;

public class Tunicas implements Producto{

	// Atributos
		private int _idProd = 1 ;
		private String _Nombre = "Tunica Niño";
		private int _Costo = 650;
		private int _Cant = 10;
		
		/* A futuro rehacer las propiedades con restricciones 	
		Ejmplo de prop con restriccion y manejo de exceptciones*/

		// propiedades
		public int get_idProd() throws Exception {
			if (_idProd != 0) {
				return _idProd;
			} else {
				throw new Exception("Error - No se encontro producto (0).");
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
		public Tunicas() {
		}

		// Constructor completo
		@Override
		public Producto getProducto() {
			Tunicas tun = new Tunicas();
			tun._idProd = _idProd;
			tun._Nombre = _Nombre;
			tun._Costo = _Costo;
			tun._Cant = _Cant;
			return tun;
		}

		@Override
		public String toString() {
			return "Tunicas [_idProd=" + _idProd + ", _Nombre=" + _Nombre + ", _Costo=" + _Costo + ", _Cant=" + _Cant + "]";
		}
}
