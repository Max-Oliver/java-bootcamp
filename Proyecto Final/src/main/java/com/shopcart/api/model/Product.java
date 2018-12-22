package com.shopcart.api.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "product")
@Api(value = "Cart Entity", description = "All data of the shop cart")
public class Product {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		@ApiModelProperty(value="id of cart", required = false)
		private Long idProduct;
		
		@Column (name = "category")
		@ApiModelProperty(value = "Category of the Product", required= false)
		private String category;
		
		@Column (name = "nameProd")
		@ApiModelProperty(value = "Name of the product", required= false)
		private String nameProd;
		
		@Column (name = "price")
		@ApiModelProperty(value = "price of the product", required = false)
		private int price;
	
		public Product() {}
		
		public Product(Long idProduct, String cate, String nameProd, int price) {
			this.idProduct = idProduct;
			this.category = cate;
			this.nameProd = nameProd;
			this.price    = price;
		}
		
		public Long getIdProduct() {
			return idProduct;
		}

		public void setIdProduct(Long idProduct) {
			this.idProduct = idProduct;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
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
		
		
}
