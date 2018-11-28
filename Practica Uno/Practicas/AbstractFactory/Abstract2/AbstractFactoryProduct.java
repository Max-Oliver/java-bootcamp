package Abstract2;

import Abstract2.Carrito.Materiales;
import Abstract2.Productos.Producto;

public abstract class AbstractFactoryProduct {

	abstract Producto getProducto(Producto _unProd);
	abstract Materiales getMateriales(Materiales _unClie);
}
