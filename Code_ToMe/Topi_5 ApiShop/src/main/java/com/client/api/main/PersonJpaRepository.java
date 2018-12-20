package com.client.api.main;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    List<Person> findAll();
}
