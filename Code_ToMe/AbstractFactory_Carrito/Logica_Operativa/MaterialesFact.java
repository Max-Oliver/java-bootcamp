package Logica_Operativa;

import Materiales_Estudio.Boligrafos;
import Materiales_Estudio.Cartuchera;
import Materiales_Estudio.Cuadernos;
import Materiales_Estudio.Materiales;
import Vestimenta.Producto;

public class MaterialesFact extends AbstractFactory{

	@Override
	Producto getProducto(String _unProd) 
	{
		return null;
	}

	@Override
	Materiales getMateriales(String _unMate) 
	{
		// TODO Auto-generated method stub
		if (_unMate == null) 
		{
			return null;
		}
		
		if ( _unMate.equalsIgnoreCase("Cartuchera")) 
		{
			
			return new Cartuchera();
			
		} else if(_unMate.equalsIgnoreCase("Cuadernos"))		
		{
			return new Cuadernos();
			
		}else if(_unMate.equalsIgnoreCase("Boligrafos")) 
		{
			return new Boligrafos();
		}
		
		return null;
	}
	
}
