package classPrueba;

public class Personas {

	private String nombre = "Carlos";
	private int Edad = 32;
	private int Alt = 178;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	}
	public int getAlt() {
		return Alt;
	}
	public void setAlt(int alt) {
		Alt = alt;
	}
	
	//Constructor de objeto
	public Personas() {}
	
	//Constructor Completo
	public Personas (String pNom, int pEdad, int pAlt) 
	{
		this.nombre = pNom;
		this.Edad = pEdad;
		this.Alt = pAlt;		
	}
	
	@Override
	public String toString() {
		return "Personas [nombre=" + nombre + ", Edad=" + Edad + ", Alt=" + Alt + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}

