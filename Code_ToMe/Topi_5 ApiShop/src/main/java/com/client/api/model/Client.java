package com.client.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "persons")
public class Client {

	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column
	    private Long idClient;   	 	
	 	@Column(name = "nickname")
	    private String nickName;
	    @Column(name = "password")
	    private String password;
		@Column(name = "first_name")
	    private String firstName;
	    @Column(name = "last_name")
	    private String lastName;

	    public Long getIdClient() {
	        return idClient;
	    }
	    
	    public void setIdClient(final Long idClient) {
	        this.idClient = idClient;
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
	    
	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(final String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(final String lastName) {
	        this.lastName = lastName;
	    }
	
}
