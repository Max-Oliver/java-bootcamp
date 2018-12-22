package com.shopcart.api.controller;

import java.sql.SQLException;
import java.util.List;

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


import com.shopcart.api.model.Product;
import com.shopcart.api.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(
        path = "Api/product",
        produces = MediaType.APPLICATION_JSON_VALUE)
        @Api(value = "products", description = "Product Into CartShop API", produces = "application/json")
public class ProductController {

	private final ProductsService service;
	
	public ProductController(final ProductsService service) {
		this.service = service;
	}
	
	@GetMapping
    @ApiOperation(value = "Get Products", notes = "Returns all Products")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No products registred"),@ApiResponse(code = 200, message = "Exits one product at least")
           
    })
    public ResponseEntity<List<Product>> getProducts() throws SQLException {
        List<Product> product = service.findAllProducts();
        if (product.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
    }
	
	
	@GetMapping("/product/{idProduct}")
    @ApiOperation(value = "Get Products", notes = "Returns the specific Product by id")
    @ApiResponses({
           @ApiResponse(code = 404, message = "Products Not Found"),
           @ApiResponse(code = 200, message = "Return The Product and OK")
          
   })
    public ResponseEntity<Product> getProductById(@PathVariable("idProduct")final Long id) throws SQLException {
		Product prod = service.ListOfProductById(id);
       if (prod.getIdProduct() == null) {
           throw new ProductNotFoundException("PRODUCT ID NOT FOUND: " + id);
       }
       return new ResponseEntity<Product>(prod, HttpStatus.OK);
   }
	
	@GetMapping("/nameProduct/{nameProduct}")
    @ApiOperation(value = "Get Product by name", notes = "Returns the specific Product by name")
    @ApiResponses({
           @ApiResponse(code = 404, message = "NOT PRODUCT FOUND"),
           @ApiResponse(code = 200, message = "Return the product and ok")
          
   })
    public ResponseEntity<Product> finProductByName(@PathVariable("nameProduct")final String name) throws SQLException {
        Product product = service.getProductByName(name);
       if (product.getNameProd() == null) {
           throw new ProductNotFoundException("PRODUCT NAME NOT FOUND: " + name);
       }
       return new ResponseEntity<Product>(product, HttpStatus.OK);
   }
    
   
     
    @GetMapping("/product/category/{category}")
    @ApiOperation(value = "Get Product by category", notes = "Returns  Product by category")
    @ApiResponses({
           @ApiResponse(code = 404, message = "No product name found"),
           @ApiResponse(code = 200, message = "Return the product and ok")
          
   })
    public ResponseEntity<List<Product>> finProductByCategory(@PathVariable("category")final  String category) throws SQLException {
      List<Product> product = service.listOfProductsByCategory(category);
       if (product.isEmpty()) {
           throw new ProductNotFoundException("PRODUCT CATEGORY NOT FOUND: " + category);
       }
       return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
   }
    
    @PostMapping("/product/{idProduct}")
    @ApiOperation(value = "Add Product", notes = "Create product")
    @ApiResponses({
           @ApiResponse(code = 409, message = "Product already exist"),@ApiResponse(code = 200, message = "Return the product and ok")
          
   })
   
   public ResponseEntity<Product> postProduct(@RequestBody final  Product product) throws SQLException {
       List<Product> products = service.findAllProducts();
       for (Product obj : products) {
           if (product.getIdProduct() == obj.getIdProduct()) {
               throw new ProductConflictExistException("Product Already Exists: " + product.getIdProduct());
           }     
       }
       service.createProduct(product);
       return new ResponseEntity<Product>(product, HttpStatus.CREATED);
   }
   
      @PutMapping("/product/{idProduct}")
       @ApiOperation(value = "Put Product", notes = "Update or Modify the Product if exist, if not exist it created in this instance")
        @ApiResponses({
           @ApiResponse(code = 404, message = "Product not found"),
           @ApiResponse(code = 200, message = "Return the update-Product and ok")
          
   }) 
      public ResponseEntity<Product> putProduct(@PathVariable(name = "idProduct")final  Long id,
           @RequestBody  Product product) throws SQLException {
               
                Product products = service.updateProduct(product);
                 if (product.getIdProduct()==null) {
           throw new ProductNotFoundException("PRODUCT  NOT FOUND: ");
       }
       return new ResponseEntity<Product>(product, HttpStatus.OK);
                
   }
      
      @DeleteMapping("/product/{idProduct}")
      @ApiOperation(value = "Delete Product", notes = "Delete product by id")
      @ApiResponses({
           @ApiResponse(code = 404, message = "Product not found"),@ApiResponse(code = 200, message = "Return  product Deleted and ok")
          
   })
   public ResponseEntity<Product> deleteProduct(@PathVariable(name = "idProduct")final Long id) throws SQLException {
       Product product = service.ListOfProductById(id);
       if (product.getIdProduct()==null) {
           throw new ProductNotFoundException("PRODUCT NOT FOUND: " + id);
       }
       service.deleteProduct(product);
       return new ResponseEntity<Product>(product, HttpStatus.OK);

   }
   
   
   
   @ResponseStatus(HttpStatus.NOT_FOUND)
       public static class ProductNotFoundException extends RuntimeException {

           public ProductNotFoundException(String message) {
               super(message);
           }
       }
   
           @ResponseStatus(HttpStatus.CONFLICT)
       public static class ProductConflictExistException extends RuntimeException {

           public ProductConflictExistException(String message) {
               super(message);
           }
       }
}
