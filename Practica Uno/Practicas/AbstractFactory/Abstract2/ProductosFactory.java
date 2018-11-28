package Abstract2;

import Abstract2.Carrito.Materiales;
import Abstract2.Productos.Producto;

public class ProductosFactory extends AbstractFactoryProduct{
	
	@Override
	Producto getProducto(Producto _unProd) {
		// TODO Auto-generated method stub
		return null;
	}

	//Aca creo que iria la Perisstencia del objeto
	@Override
	Materiales getMateriales(Materiales _unM) {
		
		//codigo de comprobacion para cargar el objeto y devolverlo para comenzar el pasa 
		//manos
		if (_unM == null) {
			return null;
		}
		
		if (_unM ) {
			
		} else {

		}
		return _unM;
	}


	
}
