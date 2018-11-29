package classPrueba;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Personas per = new Personas();
		
		System.out.println("Nombre: "+ per.getNombre() );
		System.out.println("Edad: " + per.getEdad());
		System.out.println("Altura: " + per.getAlt());
		
		System.out.println("******** Hola mundo *************");
		System.out.println(per.toString());
	}

}
