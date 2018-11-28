package Practico1;

import java.sql.Connection;

public class Singleton {

	public static void main(String[] args) {
		
		//singleton con Conexion
		 Conexion1 c = new Conexion1();
	        System.out.println(c.conectar());
	        Conexion1.enviarSQL("INSERT INTO productos VALUES('12','Camara',5000)");
	        System.out.println("Correcto!");
	        Conexion1.obtenerSQL("Select * from productos");
	        
	}	
}
	
	//Mi clase fuera del main llamada SingletonExample.
	/*public static class SingletonExample{
		
		//creo una instancia de la clase null
		private static SingletonExample singletonExample = null;

		//creo un constructor de la clase vacio
		private SingletonExample() {
		}
		
		//Creo un metodo que de tipo CLASE - SingletonExample en este caso 
		//- que se llama getInstance
		public static SingletonExample getInstance() {
			//verifico si mi Atributo singletonExcample esta vacio.
			if (singletonExample == null) {
				//como esta vacio, le asigno un metodo que esta esperando 
				//el metodo vacio que construi 
				singletonExample = new SingletonExample();
			}
			//retorno mi instancia - ya sea vacia o llena (Siempre se chekea que este vacia para entregarlo)
			return singletonExample;
		}

		//Creo metodo para dar mensajes , que retorna null 
		public void sayHello() {
			System.out.println("Que dice don todo manso?");
		}*/
		

		

