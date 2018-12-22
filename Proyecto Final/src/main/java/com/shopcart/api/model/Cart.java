package com.shopcart.api.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cart")
@Api(value = "Cart Entity", description = "All data of the shop cart")
public class Cart {
	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@ApiModelProperty(value="id of cart", required = false)
	private Long idCart;
	
	@ApiModelProperty(value = "Id of Client", required= false)
	private Long idClient;
	
	@ApiModelProperty(value = "State of cart", required= false)
	private boolean stateCart;
	
	@ApiModelProperty(value = "last Date of the buy in cart", required = false)
	private Date lastDateBuy;
	
	public Cart() {}
	
	public Cart(Long idCart, Long idClient, boolean stateCart  , Date lastDateBuy) {
		this.idCart=idCart;
		this.idClient=idClient;
		this.stateCart = stateCart;
		this.lastDateBuy = lastDateBuy;
	}

	public Long getIdCart() {
		return idCart;
	}

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public boolean isStateCart() {
		return stateCart;
	}

	public void setStateCart(boolean stateCart) {
		this.stateCart = stateCart;
	}

	public Date getLastDateBuy() {
		return lastDateBuy;
	}

	public void setLastDateBuy(Date lastDateBuy) {
		this.lastDateBuy = lastDateBuy;
	}
}
