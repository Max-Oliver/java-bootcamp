package com.shopcart.api.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopcart.api.connection.ApiConnection;
import com.shopcart.api.model.Client;
import com.shopcart.api.repository.ClientRepository;

@Service
public class ClientService {
	
		private final ClientRepository repository;
		
		@Autowired
		public ClientService(final ClientRepository reposirtory) {
			this.repository = reposirtory;
		}
	
		//Show all Clients
		public List<Client> listAllClient()throws SQLException {
			Connection cnn;
			cnn = ApiConnection.getCnn();
		      Statement query = cnn.createStatement();
		      ResultSet res = query.executeQuery("Select * from `client` ;");
		      	List<Client> listCli = new ArrayList();
		        while (res.next()) {
		            Client cli = new Client();
		            cli.setIdClient( res.getLong("idUser"));
		            cli.setFirstName(res.getString("firstName"));
		            cli.setLastName( res.getString("lastName"));
		            listCli.add(cli);
		        }
		        res.close();
		        return listCli;
		    }
		
		public Client findClientById(final Long idClient) throws SQLException {
			Connection cnn;
				cnn = ApiConnection.getCnn();
	        Statement query = cnn.createStatement();
	        ResultSet res = query.executeQuery("select * from `client` where `client`.idClient =" + idClient + " ;");
	        Client cli = new Client();
	        while (res.next()) {

	            cli.setIdClient(res.getLong("idUser"));
	            cli.setFirstName(res.getString("firstName"));
	            cli.setLastName(res.getString("lastName"));
	        }
	        res.close();
	        return cli;
		}
		
		//find Client by Name , if not found return null
		public Client findClientByFName(final String firstName) throws SQLException {
			 Connection cnn;
		        cnn = ApiConnection.getCnn();
		        Statement query = cnn.createStatement();
		        ResultSet res = query.executeQuery("Select * from `client` where  `client`.firstName = `" + firstName + "' ;");
		        Client cli = new Client();
		        while (res.next()) {
		            cli.setIdClient(res.getLong("idClient"));
		            cli.setFirstName(res.getString("firstName"));
		            cli.setLastName(res.getString("lastName"));
		        }	        
		        res.close();
		        return cli;
		    }

			/*
			 
			//List<Client> lisClient = repository.findAll();
			//for(Client cli: lisClient) {
			//if (cli.getFirstName().equals(firstName)) {
			//return cli;
			//	}
			//}
			//return null;
			//}
			 
			 */
		
		//find client by Nick Name , if Not found return null
		/*
		 public Client findClientByNickName(final String nickName) {
		 List<Client> lisClient= repository.findAll();
		 for(Client cli : lisClient) {
		 if (cli.getNickName().equals(nickName)) {
		 return cli;
		 }
		 }
		return null;
		}
		*/
		
		//Create new client and save it
		 public Client upClient(final Client cli) throws SQLException {
		      Connection cnn;
		        	cnn = ApiConnection.getCnn();
		        PreparedStatement query = cnn.prepareStatement("INSERT INTO `client` VALUES( ? , ? , ? ) ");
		        query.setLong(	1, cli.getIdClient());
		        query.setString(2, cli.getFirstName());
		        query.setString(3, cli.getLastName());
		        query.executeUpdate();
		        return cli;
		    }
		// finde on the list of clients, and update the client, or just create a new client
		public Client updateClient(final Client cli) throws SQLException{
			  Connection cnn;
			  	 cnn = ApiConnection.getCnn();
		      PreparedStatement query = cnn.prepareStatement("update `client` set `firstName` = ? , `lastName` = ? where `client`.idClient = ? ;");
				 query.setString(1, cli.getFirstName());
				 query.setString(2, cli.getLastName());
				 query.setLong(  3, cli.getIdClient());
				 query.executeUpdate();
		      
		      return cli;									
			}
			
		//Delete a specific client
		public void deleteClient(final Client cli) throws SQLException {
	        Connection cnn;
	        	cnn = ApiConnection.getCnn();
	        PreparedStatement query = cnn.prepareStatement("delete from `client` where `client`.idClient = " + cli.getIdClient() + " ;");
	        	query.executeUpdate();
		}
}

