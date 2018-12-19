package com.shopcart.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 @author Maximiliano
 *
 **/

@Entity
@Table(name = "client")
@ApiModel(value = "Client Entity", description = "Complete data of a Entity Client of ShopCart")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@ApiModelProperty(value = "The id of Clients", required = false)
	private Long idClient;
	
	@Column(name = "firstName")
	@ApiModelProperty(value="The firstName of the Client", required = true)
	private String fistName;

	@Column(name = "lastName")
	@ApiModelProperty(value="The LastName of the Client", required = true)
	private String lastName;
	
	@Column(name = "nickName")
	@ApiModelProperty(value="The NickName of the Client", required = true)
	private String nickName;
	
	@Column(name = "password")
	@ApiModelProperty(value="The password of the Client", required = true)
	private String password;
	
	public Client() {
		
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
