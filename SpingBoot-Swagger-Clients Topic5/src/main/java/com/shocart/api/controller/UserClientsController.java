package com.shocart.api.controller;

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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopcart.api.model.Client;
import com.shopcart.api.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestControllerAdvice
@RequestMapping(
		path = "/user/Clients",
		produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "uses", description = "Clients API", produces = "application/json" )
public class UserClientsController {

	private final ClientService service;
	
	public UserClientsController(final ClientService service) {
		this.service = service;
	}
	
	/*Method GET*/
	@GetMapping
	@ApiOperation(value ="Get Clients", notes = "Returns all Clients")
	@ApiResponses({
			@ApiResponse( code = 204, message = "No clients registred"),
			@ApiResponse(code = 200, message = "Exists one client at least")
	})

	public ResponseEntity<Client> getClients(){
		List<Client> clients = service.findAllClient();
		if (clients.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);//NO HAY CONTENIDO
		}
		return new ResponseEntity(clients, HttpStatus.OK);
	}

	//METHOS = GET by id clients
	@GetMapping("/user/{idClients}")
	@ApiOperation(value = "Get Clients", notes = "Returns user by id")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Clients id Not Found... :( ")
	})
	
	public ResponseEntity<Client> getClientsById(@PathVariable("idClient") final Long id){
		final Client client = service.findClientById(id); 
		if (Objects.isNull(client)) {
			throw new ClientNotFoundException("CLIENT NOT FOUND: " + id);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK );
	}
	
	//METHOS = GET by nickName
	@GetMapping("/user/clientNick/{nickName}")
	@ApiOperation(value = "Get client by NickName", notes = "Returns clients by NickName")
	@ApiResponses({
			 @ApiResponse(code = 404, message = "Client Not Found"),
			 @ApiResponse(code = 200, message = "Return ok and the spesific Client")
	})
	public ResponseEntity<Client> getClientByNickName(@PathVariable("nickName") final String nickName){
	Client client = service.findClientByNickName(nickName);
	if (Objects.isNull(client)) {
		throw new ClientNotFoundException("CLIENT NOT FOUND: " + nickName);
	}
	return new ResponseEntity<Client>( client , HttpStatus.OK);
}
	
	//METHOS = GET By FirstName
@GetMapping("/user/name/{firstName}")
@ApiOperation(value = "Get name of clients by firstName", notes = "Returns clients by first name")
@ApiResponses({
	@ApiResponse(code = 404, message = "Client not found"),
	@ApiResponse(code = 200, message = "Return ok  and Client")
})

public ResponseEntity<Client> getClientsByName( @PathVariable("firstName") final String firstName) {
	Client clie = service.findClientByFName(firstName);
	if (Objects.isNull(clie)) {
		throw new ClientNotFoundException("CLIENT NOT FOUND: ");
	}
	return new ResponseEntity<Client>(clie, HttpStatus.OK);
}

//METHOS = POST by id ClIENT
@PostMapping("/user/{idClient}")
@ApiOperation(value = "Post clients", notes = "Create Clients by POST")
@ApiResponses({
		@ApiResponse (code = 409, message = "Client id already exists :)"),
		@ApiResponse (code = 409, message = "Cient NickName already exists :("),
		@ApiResponse (code = 200, message = "Returns ok and the Client")	
})

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

//METHOS = PUT
@PutMapping("/user/{idClient}")
@ApiOperation(value = "Put Clients", notes = "Modify Clients by ID")
@ApiResponses({
		@ApiResponse(code = 404, message = "Client not Found :(" ),
		@ApiResponse(code = 200, message = "Return ok and the Client modified")
})

public ResponseEntity<Client> putClient(@PathVariable( name = "idClient") final Long id, @RequestBody final Client client){
			Client cli = service.updateClient(client);
			if (Objects.isNull(client)) {
				throw new ClientNotFoundException("CLIENT NOT FOUND: " );
			}
			return new ResponseEntity<Client>(client, HttpStatus.OK);
}
//METHOS = DELETE
@DeleteMapping("/user/{idClient}")
@ApiOperation(value = "Delete Client", notes = "Delete the client by id")
@ApiResponses({
	@ApiResponse(code = 404, message = "Client not Found :("),
	@ApiResponse(code = 200, message = "Return OK and the Client")
})

public ResponseEntity<Client> deleteClient(@PathVariable(name = "idCLient") final Long id){
	Client cli = service.findClientById(id);
	if (Objects.isNull(cli)) {
		throw new ClientNotFoundException("CLIENT NOT FOUND: " + id);
	}
	service.deleteClient(cli);
	return new ResponseEntity<Client>(cli, HttpStatus.OK);
}


@ResponseStatus(HttpStatus.NOT_FOUND)
public static class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}

}//final class




