package com.client.api.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.client.api.model.Client;
import com.client.api.service.ClientService;


@RestController
@RequestMapping(
        path = "/Client",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	@RestController
	@RequestMapping(
	        path = "/Client",
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public class PersonRestController {

	    private final ClientService service;

	    public PersonRestController(final ClientService service) {
	        this.service = service;
	    }

	    @GetMapping
	    public ResponseEntity<Client> getClients(){
			List<Client> clients = service.findAllClient();
			if (clients.isEmpty()) {
				return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);//NO HAY CONTENIDO
			}
			return new ResponseEntity(clients, HttpStatus.OK);
		}
	    @GetMapping("/client/{idClient}")
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Client> getClientsById(@PathVariable("idClient") final Long id){
			 Client client = service.findClientById(id); 
			if (Objects.isNull(client)) {
				throw new ClientNotFoundException("CLIENT NOT FOUND: " + id);
			}
			return new ResponseEntity<Client>(client, HttpStatus.OK );
		}

	    
	    @GetMapping("/user/NickName/{nickName}")
		public ResponseEntity<Client> getClientByNickName(@PathVariable("nickName") final String nickName){
		Client client = service.findClientByNickName(nickName);
		if (Objects.isNull(client)) {
			throw new ClientNotFoundException("CLIENT NOT FOUND: " + nickName);
		}
		return new ResponseEntity<Client>( client , HttpStatus.OK);
	}
	    
	    
	    @GetMapping("/client/name/{firstName}")
	    public ResponseEntity<Client> getClientsByName( @PathVariable("firstName") final String firstName) {
	    	Client clie = service.findClientByName(firstName);
	    	if (Objects.isNull(clie)) {
	    		throw new ClientNotFoundException("CLIENT NOT FOUND: ");
	    	}
	    	return new ResponseEntity<Client>(clie, HttpStatus.OK);
	    }
	    
	    @PostMapping("/client/{idClient}")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Client> postClient(@RequestBody final Client client){
	    		List<Client> LisClient = service.findAllClient();
	    		for(Client cli : LisClient) {
	    			if (cli.getIdClient() == client.getIdClient()) {
	    				throw new ClientNotFoundException("CLIENT ID ALREADY EXISTS: " + client.getIdClient());
	    			}
	    			if (cli.getNickName() == client.getNickName()) {
	    				throw new ClientNotFoundException("CLIENT NICKNAME ALREADY EXISTS: " + client.getNickName());
	    			}
	    		}
	    		service.createClient(client);
	    		return new ResponseEntity<Client>(client, HttpStatus.CREATED);
	    }

	  
	    @PutMapping("/client/{id}")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Client> putPerson(@PathVariable(name = "idClient") final Long id, 
	    											 @RequestBody final Client client){

	         Client cli = service.updateClient(client);
	         cli.setIdClient(id);
	        if (Objects.isNull(client)) {
	            throw new ClientNotFoundException("CLIENT NOT FOUND: " );
	        }
	        return new ResponseEntity<Client>(client, HttpStatus.OK);
	    }

	    @DeleteMapping("/client/{idClient}")
	    public ResponseEntity<Client> deleteClient(@PathVariable(name = "idCLient") final Long id){
	    	Client cli = service.findClientById(id);
	    if (Objects.isNull(cli)) {
	    		throw new ClientNotFoundException("CLIENT NOT FOUND: " + id);
	    	}
	    	service.deleteClient(cli);
	    	return new ResponseEntity<Client>(cli, HttpStatus.OK);
	    }
   
	}
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	  public static class ClientNotFoundException extends RuntimeException {
	      /**
		 *  (oªo)?
		 */
		private static final long serialVersionUID = 1L;

		public ClientNotFoundException(String message) {
	        super(message);
	    }
	 }
}
