package com.shopcart.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ApiConnection {

	private static Connection cnn = null;
	private static String url;
	private static String user;
	private static String pswd;
	private static String drvDb;
	
	private final static Logger log_DB = Logger.getLogger("Connecion_DataBase");

	@Autowired
	public static void setUrl(@Value("${spring.datasource.url}") String urlDataBase) {
		url = urlDataBase;
	}

	@Autowired
	public static void setUser(@Value("${spring.datasource.username}") String username) {
		user = username;
	}
	
	@Autowired
	public static void setPswd(@Value("${spring.datasource.password}") String pass) {
		pswd = pass;
	}
	
	@Autowired
	public static void setDrvDb(@Value("${spring.datasource.Driver}") String driverdb) {
		drvDb = driverdb;
	}
	private ApiConnection() {}
	
	
	public static Connection getCnn() {
	   try {
		if (cnn == null){
			Runtime.getRuntime().addShutdownHook(new getClose());
			Class.forName(drvDb);
		   	 cnn = DriverManager.getConnection(url, user, pswd);
		     log_DB.info("Connected... Syncrhonized Singleton.");               
		   }		          
		return cnn;	   
	 } catch (ClassNotFoundException | SQLException ex) { 
		 ex.printStackTrace();
		 log_DB.info("Error in the Connection: /n");
		 throw new RuntimeException("Error al crear la conexion", ex);
		 //loggin log no puede guardar stackTrace
	 }
	}
	
	static class getClose extends Thread{
		@Override
	   public void run(){	 
			try {
				cnn.close();
				log_DB.info("Connection is closed");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log_DB.info("Error closing the Connection: ");
				throw new RuntimeException(ex);
			   }   
		  }
	}
	
}

