package com.shopcart.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopcart.api.model.Cart;

@Repository
public interface CartShopRepository extends JpaRepository<Cart, Long>{
	List<Cart> findAll();
}
