package Practico1;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class Conect {
	private static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
		
		private Conect() {
		}
		
		public static Connection getCon() {

			if (con == null) {						
			// Create a variable for the connection string.
			String connectionUrl = "jdbc:sqlserver://LocalHost:444;databaseName=practica1;integratedSecurity=true;";

			// Declare the JDBC objects.			
        	try {
        		// Establish the connection.
        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            	con = DriverManager.getConnection(connectionUrl);
            	
            
            		// Create and execute an SQL statement that returns some data.
            		String SQL = "insert into cosas (IdProduct,Nombre,Precio,Stock) values(5,'Silla',900,12)";
            		stmt = con.createStatement();
            		rs = stmt.executeQuery(SQL);
            		
            		System.out.println("Se guardo.. o ingreso");
            		//Iterate through the data in the result set and display it.
            		while (rs.next()) {
            			System.out.println(rs.getString(0) + " " + rs.getString(1));
            		}
        	}
			
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return con;


    
	}
}
	

