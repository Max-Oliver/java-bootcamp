package com.shopcart.api.controller;


import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Logger;
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

import com.shopcart.api.model.Cart;
import com.shopcart.api.model.Client;
import com.shopcart.api.model.ClientCart;
import com.shopcart.api.model.Product;
import com.shopcart.api.service.CartService;
import com.shopcart.api.service.ClientService;
import com.shopcart.api.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping(
			path="api/cart",
			produces = MediaType.APPLICATION_JSON_VALUE )
@Api(value = "cart", description =" Api of shop cart", produces = "application/json")
public class CartController {

		private  final CartService serviceCart;
		private  final ClientService  serviceCli;
		private  final ProductsService serviceProd;
		
		
	public CartController(final CartService serviceC,final ClientService serviceCli, final ProductsService seriviceProd ) {
		this.serviceCart = serviceC;
		this.serviceCli = serviceCli;
		this.serviceProd = seriviceProd;
	}
		
	//show carts by clients
	@GetMapping("/cart/{idClient}")
    @ApiOperation(value = "Get carts by Clients", notes = "Returns all Carts of The client ")
    @ApiResponses({
        @ApiResponse(code = 204, message = "There is no Cart fot that Client"),
        @ApiResponse(code = 200, message = "Yes, We have one or more carts to show you.")

    })
    public ResponseEntity<List<Cart>> getCartsByClient(@PathVariable("idClient") final Long idClient) throws SQLException {
        List<Cart> cart = serviceCart.ListOfCartByClient(idClient);
        if (cart.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cart>>(cart, HttpStatus.OK);
    }
	
	// show products by carts 
	@GetMapping("/cart/Product/{idcart}")
    @ApiOperation(value = "Get Products", notes = "Show all products of a cart")
    @ApiResponses({
        @ApiResponse(code = 204, message = "There   is no Cart fot that Client"),
        @ApiResponse(code = 200, message = "Yes, We have one or more products to show you.")

    })
    public ResponseEntity<List<ClientCart>> getProductsByCart(@PathVariable("idcart") final Long idCart) throws SQLException {
        List<ClientCart> cart = serviceCart.ListOfProductsByCars(idCart);
        if (cart.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ClientCart>>(cart, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Save Cart", notes = "Add a new Client to the cart")
    @ApiResponses({
        @ApiResponse(code = 409, message = "The Cart Was Saven Before"),
        @ApiResponse(code = 200, message = "Return The cart saved and ok"),
        @ApiResponse(code = 404, message = "Client Not Found")

    })
    @PostMapping
    public ResponseEntity<Cart> saveCartOfClient(@RequestBody final Cart cart) throws SQLException, NotFoundException {
			List<Cart> listCart = serviceCart.ListOfCartByClient(cart.getIdClient());
				     Client cli = serviceCli.findClientById(cart.getIdClient());
        if (cli.getIdClient() == null) {
            throw new NotFoundException("Client Not Found");
        }
        for (Cart car : listCart) {
            if (car.isStateCart() == false) {
            	throw new ConflictException("The Cart Was Saved");
            }
            if (cart.getIdCart() == car.getIdCart()) {
                throw new ConflictException("Cart Already Exists please insert Another Id: " + cart.getIdCart());
            }

        }
        serviceCart.saveCartOfClient(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

	@ApiOperation(value = "Add items", notes = "Add items into the cart")
    	@ApiResponses({
	        @ApiResponse(code =  409,  	message = "Product Saved"),
	        @ApiResponse(code =  200, 	message = "Return The Item "),
	        @ApiResponse(code =  404,   message = "Product Not Found")
    })
	
	//post product
	@PostMapping("/cart/product/{idProduct}")
    public ResponseEntity<ClientCart> addItemsCart(@PathVariable(name = "idProduct") final Long id, 
    											   @RequestBody final ClientCart cliCart) throws SQLException, NotFoundException {
        	List<ClientCart> listProd = serviceCart.ListOfProductsByCars(cliCart.getIdCart());
        				   Client cli = serviceCli.findClientById(cliCart.getIdClient());
        				   Cart   car = serviceCart.findCartById(cliCart.getIdCart());
        				 Product prod = serviceProd.ListOfProductById(cliCart.getIdProduct());
        if (cli.getIdClient() == null) {
            throw new NotFoundException("Client Not Found: ");
        }
        if (car.getIdCart() == null) {
            throw new NotFoundException("Cart Not Found");
        }
        if (prod.getIdProduct() == null) {
            throw new NotFoundException("Product Not Found");
        }
        for (ClientCart Ccart : listProd) {
            if (cliCart.getIdProduct() == Ccart.getIdProduct()) {
            	//must to be other exception but not time to search that.
                throw new OkException("Product already added succesless! " + cliCart.getIdProduct());
            }

        }
        
        serviceCart.addItemsToCart(cliCart.getIdProduct(), cliCart);
        serviceCart.setTotImport(cliCart);
        //Return the Product added in the cart by idCart and idProduct, http status to send Created message
        return new ResponseEntity<ClientCart>(serviceCart.ProductByCart(cliCart.getIdCart(), cliCart.getIdProduct()), HttpStatus.CREATED);
    }
	
	//put
	@PutMapping("/cart/buy/{idcart}")
    @ApiOperation(value = "Client Cart", notes = "Register Clients To Cart")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Carts Not Found "),
        @ApiResponse(code = 409, message = "Cart is in Use"),
        @ApiResponse(code = 409, message = "Cart is Empy"),
        @ApiResponse(code = 200, message = "Return cart with modifys")

    })
    public ResponseEntity<Cart> buyCart(@PathVariable(name = "idcart") final Long idCart) throws SQLException, NotFoundException {
        		Cart cart = serviceCart.findCartById(idCart);
        		//Client cli= serviceCli.findClientById(cart.getIdClient());
        	int totImport = serviceCart.getTotImport(cart.getIdCart());
        		
        if(totImport == 0){
        	throw new ConflictException("Cart is Empty");
        }
        if (cart.getIdCart() == null) {
            throw new NotFoundException("Carts Not Found: " + idCart);
        }
        if(cart.isStateCart() == true){
            throw new ConflictException("Cart is in Use for another Client:" );
        }

        Cart concretBuy = serviceCart.concretBuy(cart);
        return new ResponseEntity<Cart>(concretBuy, HttpStatus.OK);
        } 
    
	//Delete Product by id Product
	@DeleteMapping("/cart/product/{idProduct}")
    @ApiOperation(value = "Delete Product", notes = "Delete Product in the cart")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Product Not Found"),
        @ApiResponse(code = 409, message = "Cart is empy"),
        @ApiResponse(code = 404, message = "Cart is in Use"),
        @ApiResponse(code = 200, message = "Return Product Deleted")
           
    })
    public ResponseEntity<Product> deleteProduct(@PathVariable(name = "idProduct")final Long idProduct,
    											 @RequestBody final Long idCart) throws SQLException, NotFoundException {
			Product prod  = serviceProd.ListOfProductById(idProduct);
			Cart    cart  = serviceCart.findCartById(idCart);
		    int totImport = serviceCart.getTotImport(idCart);
		    
		if(totImport == 0){
	           throw new ConflictException("Cart is empy");
	    }
        if (prod.getIdProduct() == null) {
            throw new NotFoundException("Product Not Found - " + idProduct);
        }
        if (cart.isStateCart() == true){
             throw new ConflictException("Cart is in Use");  
        }
        
        serviceCart.delProdCart(idCart,idProduct);
        return new ResponseEntity<Product>(prod, HttpStatus.OK);
    }
	
	//Delete cart By Id
	@DeleteMapping("/cart/{idcart}")
    @ApiOperation(value = "Delete Cart", notes = "Delete Cart by Id")
    @ApiResponses({
    	 @ApiResponse(code = 404, message = "Product Not Found"),
         @ApiResponse(code = 409, message = "Cart is empy"),
         @ApiResponse(code = 404, message = "Cart is in Use"),
         @ApiResponse(code = 200, message = "Return Cart Deleted")      
    })
    public ResponseEntity<Cart> deleteCart(@PathVariable(name = "idcart")final Long idCart) throws SQLException, NotFoundException {
        	Cart cart = serviceCart.findCartById(idCart);
        if (cart.getIdCart() == null) {
            throw new NotFoundException("Product Not Found - " + idCart);
        }
        serviceCart.delCart(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
	
	// Class to Exception - No time to search the others already created..
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ConflictException extends RuntimeException {

        public ConflictException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public static class OkException extends RuntimeException {
        public OkException(String message) {
            super(message);
        }
    }
	
}

