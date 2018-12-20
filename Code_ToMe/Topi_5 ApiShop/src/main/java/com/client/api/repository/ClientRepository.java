package com.client.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.client.api.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	List<Client> findAll();
}
