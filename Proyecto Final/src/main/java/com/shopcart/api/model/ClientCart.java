package com.shopcart.api.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "client_cart")
@Api(value = "Cart Entity", description = "All data of the shop cart")
public class ClientCart {

	
	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@ApiModelProperty(value="id of cart", required = false)
	private Long idCart;
	
	@ApiModelProperty(value = "Id of the Product", required= false)
	private Long idProduct;
	
	@ApiModelProperty(value = "Id of the Client", required= false)
	private Long idClient;
	
	@ApiModelProperty(value = "Total Import of the Client", required = false)
	private int totImport;

	@ApiModelProperty(value = "Name of the product", required = false)
	private String nameProd;

	//trasient the next Vars is not Persistence on the bd
	@Transient
	private int price;
	private int items;
	
	public ClientCart() {}
	
	public ClientCart(Long idCart, Long idP, Long idCli, int TotImp, String nameP) {
		this.idCart = idCart;
		this.idProduct = idP;
		this.idClient = idCli;
		this.totImport = TotImp;
		this.nameProd = nameP;
		
	}
	
	public Long getIdCart() {
		return idCart;
	}
	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public int getTotImport() {
		return totImport;
	}
	public void setTotImport(int totImport) {
		this.totImport = totImport;
	}
	public String getNameProd() {
		return nameProd;
	}
	public void setNameProd(String nameProd) {
		this.nameProd = nameProd;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}

}
