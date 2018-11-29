package Logica_Operativa;

import Materiales_Estudio.Materiales;
import Vestimenta.Calzado;
import Vestimenta.Mochilas;
import Vestimenta.Producto;
import Vestimenta.Tunicas;

public class ProductosFactor extends AbstractFactory{

	@Override
	Producto getProducto(String _unProd) 
	{
		if (_unProd == null) {
			return null;
		}
		
		if (_unProd.equalsIgnoreCase("Mochila")) 
		{
			return new Mochilas();
			
		} else if(_unProd.equalsIgnoreCase("Tunica"))
		{
			return new Tunicas();
			
		}else if(_unProd.equalsIgnoreCase("Calzado")) 
		{
			return new Calzado();
		}
		return null;
	}

	
	@Override
	Materiales getMateriales(String _unM) 
	{
		return null;
	}
}
