package com.shopcart.api.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.shopcart.api.connection.ApiConnection;
import com.shopcart.api.model.Product;


@Service
public class ProductsService {

	public List<Product> findAllProducts() throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("select * from product");
        	List<Product> listProducts = new ArrayList();
        		while (res.next()) {
        			Product prod = new Product();
		            prod.setIdProduct(res.getLong("idProduct"));
		            prod.setNameProd(res.getString("nameProduct"));
		            prod.setCategory(res.getString("category"));
		            prod.setPrice(res.getInt("price"));
		            listProducts.add(prod);
		        }
        		res.close();
        return listProducts;
    }
	
	public Product ListOfProductById(final Long idProd) throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        	Product prod = new Product();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("select * from `product` where `idProduct` = "+idProd+" ;");        
	        while (res.next()) {           
	           
	        	prod.setIdProduct(res.getLong("idProduct"));
	            prod.setNameProd(res.getString("nameProduct"));
	            prod.setCategory(res.getString("category"));
	            prod.setPrice(res.getInt("price"));
	           
	        }
        res.close();
        return prod;
    }

	public List<Product> listOfProductsByCategory(final String category) throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("select * from product where  category='" + category + "'");
        	List<Product> listProdById = new ArrayList();
        	while (res.next()) {
        		Product prod = new Product();
	            	prod.setIdProduct(res.getLong("idProduct"));
	            	prod.setNameProd(res.getString("nameProduct"));
	            	prod.setCategory(res.getString("category"));
	            	prod.setPrice(res.getInt("price"));
	            	listProdById.add(prod);
	        }
	        res.close();
        return listProdById;
    }
	
	public Product getProductByName(final String nameProd) throws SQLException {
        Connection cnn;
        cnn = ApiConnection.getCnn();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("Select * From `product` where  `product`.nameProd ='" + nameProd + "' ;");
        Product pProd = new Product();
        while (res.next()) {          
            pProd.setIdProduct(res.getLong("idProduct"));
            pProd.setNameProd(res.getString("nameProduct"));
            pProd.setCategory(res.getString("category"));
            pProd.setPrice(res.getInt("price"));         
        }
        res.close();
        return pProd;
    }
	
	public Product createProduct(final Product pProd) throws SQLException {
        Connection cnn;
        cnn = ApiConnection.getCnn();
        PreparedStatement query = cnn.prepareStatement("insert into `product` values(?, ?, ?, ?);");
        query.setLong(  1, pProd.getIdProduct());
        query.setString(2, pProd.getNameProd());
        query.setString(3, pProd.getCategory());
        query.setFloat( 4, pProd.getPrice());
        query.executeUpdate();
        return pProd;
    }
	
	public Product updateProduct(final Product prod) throws SQLException {
        Connection cnn;
        cnn = ApiConnection.getCnn();
        PreparedStatement query = cnn.prepareStatement("update `product` set `nameProduct` =  ? ,  category = ?  ,  price = ? where idProduct=?");
        query.setString(1, prod.getNameProd());
        query.setString(2, prod.getCategory());
        query.setDouble(3, prod.getPrice());
        query.setLong(  4, prod.getIdProduct());
        query.executeUpdate();
        return prod;
    }
	
	public void deleteProduct(final Product product) throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        PreparedStatement query = cnn.prepareStatement("delete from `product` where `idProduct` = " + product.getIdProduct() + ";");
        	query.executeUpdate();
    }

}
