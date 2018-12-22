package com.shopcart.api.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopcart.api.connection.ApiConnection;
import com.shopcart.api.model.Cart;
import com.shopcart.api.model.ClientCart;

import javassist.NotFoundException;

@Service
public class CartService {
	
	
	// list  all carts by Clients
	public List<Cart> ListOfCartByClient(final Long idClient) throws SQLException{
		Connection cnn;
		cnn = ApiConnection.getCnn();
		Statement qry = cnn.createStatement(); // Switch sentence , for StoreProcedure no time right now
		ResultSet res = qry.executeQuery("Select * from `cart` where `cart`.idClient ="+ idClient+ " ;");
		  	
			List<Cart> listCart = new ArrayList();
			while (res.next()) {
				Cart cart = new Cart();				
				cart.setIdClient(idClient);
				cart.setIdCart(res.getLong("idcart"));
				cart.setStateCart(res.getBoolean("stateOfBuy"));
				cart.setLastDateBuy(res.getDate("lastDateBuy"));
				listCart.add(cart);
			}
		res.close();
		return listCart;
	}
	
	//find cart by id
	public Cart findCartById(final Long idCart) throws SQLException, NotFoundException{
		Connection cnn;
			cnn = ApiConnection.getCnn();
		Statement qry = cnn.createStatement(); // Switch sentence , for StoreProcedure no time right now
		ResultSet res = qry.executeQuery("Select * from `cart` where `cart`.idcart ="+ idCart+ " ;");
			if(res == null) {
					throw new NotFoundException("Cart Not Found");
				}else if(res.next()) {
					Cart cart = new Cart();				
					cart.setIdCart(idCart);
					cart.setIdClient(res.getLong("idClient"));
					cart.setStateCart(res.getBoolean("stateOfBuy"));
					cart.setLastDateBuy(res.getDate("lastDateBuy"));

					res.close();
					return cart;
				}
			return null;			
	}
	
	public List<ClientCart> ListOfProductsByCars(final Long idCart) throws SQLException{
		Connection cnn;
			cnn = ApiConnection.getCnn();
		Statement qry = cnn.createStatement(); // Switch sentence , for StoreProcedure no time right now
		ResultSet res = qry.executeQuery("Select * from `client_cart` inner join `product` on (`client_cart`.idProduct = `product`.idProduct) where `client_cart`.idcart = " + idCart + " ;");
		  	
			List<ClientCart> listProdInCart = new ArrayList();
				while (res.next()) {
					ClientCart Clicart = new ClientCart();				
					Clicart.setIdCart(idCart);
					Clicart.setIdClient(res.getLong("idClient"));
					Clicart.setIdProduct(res.getLong("idProduct"));
					Clicart.setNameProd(res.getString("nameProd"));
					Clicart.setTotImport(res.getInt("stateOfBuy"));
					Clicart.setItems(res.getInt("items"));
					Clicart.setPrice(res.getInt("price"));			
					listProdInCart.add(Clicart);
				}
				res.close();
		return listProdInCart;
	}
	
	public ClientCart ProductByCart(final Long idCart, final Long idProduct) throws SQLException {
	        Connection cnn;
	        cnn = ApiConnection.getCnn();
	        Statement query = cnn.createStatement();
	        ResultSet res = query.executeQuery("select * from `client_cart` inner JOIN `product` ON (`product`.idProduct = `client_cart`.idProduct) "
	        		+ "where `client_cart`.idCart =" + idCart + " and"
	                + "`client_cart`.idProduct =" + idProduct + " ;");
	        ClientCart item = new ClientCart();
	        while (res.next()) {
	            item.setIdCart(idCart);
	            item.setIdClient(res.getLong("idUser"));
	            item.setIdProduct(res.getLong("idProduct"));
	            item.setNameProd(res.getString("nameProduct"));
	            item.setItems(res.getInt("items"));
	            item.setPrice(res.getInt("price"));
	            item.setTotImport(res.getInt("totalImport"));
	        }
	        res.close();
	        return item;
	    }
	 
    public Cart saveCartOfClient(final Cart cart) throws SQLException {
	        Connection cnn;
	        cnn = ApiConnection.getCnn();
	        PreparedStatement query = cnn.prepareStatement("insert into cart  values(?, ?, ?, ?)");
	        	cart.setStateCart(false);
	        	cart.setLastDateBuy(null);
	        query.setLong(		1, cart.getIdClient());
	        query.setLong(		2, cart.getIdCart());
	        query.setBoolean(	3, cart.isStateCart());
	        query.setDate(		4, null);
	        query.executeUpdate();
	        	return cart;
	    }
	 
	public Cart concretBuy(final Cart mycart) throws SQLException{
		Connection cnn;
		cnn = ApiConnection.getCnn();
	
		PreparedStatement query = cnn.prepareStatement("update `cart` set `stateOfBuy` = ? ,`lastDateBuy` = ? where `cart`.idcart = " + mycart.getIdCart() + " ;");
        query.setInt(	1, 1);
        query.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        query.executeUpdate();
        mycart.setLastDateBuy(new java.sql.Date(System.currentTimeMillis()));
        mycart.setStateCart(true);
        	return mycart; 
	}
	
    public void addItemsToCart(final Long id, final ClientCart pProd) throws SQLException {
	   Connection cnn;
	        cnn = ApiConnection.getCnn();
	      PreparedStatement query = cnn.prepareStatement("insert into `client_cart` values( ? , ? , ? , ? , ? )");
	        query.setLong(	1, pProd.getIdCart());
	        query.setLong(	2, pProd.getIdClient());
	        query.setLong(	3, pProd.getIdProduct());
	        query.setInt( 	4, pProd.getItems());
	        query.setFloat( 5, pProd.getTotImport());
	        query.executeUpdate();
	    }
	
    public void setTotImport(final ClientCart items) throws SQLException {
        Connection cnn;
        cnn = ApiConnection.getCnn();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("select price from product where idProduct=" + items.getIdProduct() + " ;");
        while (res.next()) {
            items.setTotImport(items.getItems() * (res.getInt("price")));
        }
        PreparedStatement query1 = cnn.prepareStatement("update `client_cart` set `totImport` =" + items.getTotImport() + 
        											   " where `client_cart`.idProduct =" + items.getIdProduct() + " ;");
        query1.executeUpdate();
        res.close();

    }

    public int getTotImport(final Long idCart) throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        Statement query = cnn.createStatement();
        ResultSet res = query.executeQuery("select sum(totImport) from `client_cart` where `client_cart`.idCart = " + idCart + " ;");
        	res.next(); 
        	int Importe = res.getInt(1);
             
       
        	res.close();
        return Importe;        
    }

    public void delCart(final Cart cart) throws SQLException {
        Connection cnn;
        		cnn = ApiConnection.getCnn();
        PreparedStatement query1 = cnn.prepareStatement("delete from `client_cart` where `client_cart`.idCart = " + cart.getIdCart() + " ;");
        	query1.executeUpdate();
        PreparedStatement query2 = cnn.prepareStatement("delete from `cart` where `idCart` = " + cart.getIdCart() + " ;");
        	query2.executeUpdate();  
    }

    public void delProdCart(final Long idCart, final Long idProd) throws SQLException {
        Connection cnn;
        	cnn = ApiConnection.getCnn();
        PreparedStatement query = cnn.prepareStatement("delete from `client_cart` where `client_cart`.idcart = " + idCart + " and `client_cart`.idProduct = " + idProd + " ;");
        	query.executeUpdate();
    }


}
