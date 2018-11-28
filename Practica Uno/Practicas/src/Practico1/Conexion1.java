package Practico1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion1 {
	
    private final static String controlador = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/practica1";
    
    String usuario = "root";
    String clave = "" ;
    static Connection conector;
    
    public Conexion1(){
        try{
            Class.forName(controlador);
            conector = (Connection) DriverManager.getConnection(url,usuario,clave);
            
            System.out.println("Conectado");
        } catch (SQLException | ClassNotFoundException ex){}
    }
    public static Connection conectar(){
        return conector;
    	}   

    // metodo para enviar sql
    public static void enviarSQL(String sql){
        try {
        	//creo objeto tipo conexion
            Conexion1 con = new Conexion1();
            //creo la instancia de la consulta
            Statement estado = con.conector.createStatement();
            //msj con el sql ya escrito
            System.out.println(">> SQL1: "+sql);
            estado.execute(sql);
            estado.close();
        }catch(SQLException ex){}
    }
    public static ResultSet obtenerSQL(String sql){
        ResultSet dato = null;
        try {
            Conexion1 con = new Conexion1();
            Statement estado = con.conector.createStatement();
            System.out.println("## SQL2: "+sql);           
            estado.execute(sql);
            dato = estado.getResultSet();   
    
    		while (dato.next()) {
    			System.out.println(dato.getInt(0) + " " + dato.getString(1) + " " + dato.getInt(2));
    		}	
        }catch(SQLException ex){
        	
        }
        
        return dato;
    }
    





}


