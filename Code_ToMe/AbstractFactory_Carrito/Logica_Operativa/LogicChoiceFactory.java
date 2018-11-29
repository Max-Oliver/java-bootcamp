package Logica_Operativa;

public class LogicChoiceFactory {

	public static AbstractFactory getFactory(String Choice) 
	{
		if (Choice.equalsIgnoreCase("Materiales")) 
		{
			return new MaterialesFact();
		
		}else if (Choice.equalsIgnoreCase("Productos")) 
		{
			return new ProductosFactor();				
		}
		
		return null;
	}
}
