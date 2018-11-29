package Logica_Operativa;

import Materiales_Estudio.Materiales;
import Vestimenta.Producto;

public abstract class AbstractFactory {

	abstract Producto getProducto(String _unProd);

	abstract Materiales getMateriales(String _unMate);
	
}
