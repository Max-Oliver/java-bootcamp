package Abstract2.Carrito;

public class Cartuchera implements Materiales{

		//atributoss // datos de prueba
		private int _id = 1;
		private String _Nombre = "BluLise";
		private int _Cant = 10;
		private int _Precio = 150;
		
		//propiedades
		public int getId() 
		{
			return _id;
		}
		public void setId(int ID) 
		{
			this._id = ID;	
		}
		
		public String getNombre()
		{
			return _Nombre;
		}
		public void setNombre(String Nom) 
		{
			this._Nombre = Nom;
		}
		
		public int getCant() 
		{
			return _Cant;
		}
		public void setCant(int pCant) 
		{
		 this._Cant = pCant;
		}
		
		public int getPrecio() 
		{
			return _Precio;
		}
		public void setPrecio(int pPrecio) 
		{
			this._Precio = pPrecio;
		}
		
		public Cartuchera(int pID ,String pNom ,int pCant, int pPrecio) 
		{
			this._id = pID;
			this._Nombre = pNom;
			this._Cant = pCant;
			this._Precio = pPrecio;	
		}
		
		
		@Override
		public void Things(int pID ,String pNom ,int pCant, int pPrecio) 
		{
			this._id = pID;
			this._Nombre = pNom;
			this._Cant = pCant;
			this._Precio = pPrecio;
			
		}
	
		 static void ToString(Cartuchera _unaC)
		   {
		      System.out.println("\n ID: " + _unaC._id );
		      System.out.println(" Nombre: " + _unaC.getNombre() );
		      System.out.println(" Cantidad: " + _unaC.getCant() );
		      System.out.println(" Precio: " + _unaC.getPrecio() );
		   
		   }
}
