package com.orudoi.spring_security.repository;

import com.orudoi.spring_security.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getById(Long id);
    //List<Product> getAll();
    boolean add(Product product);
    boolean delete(Long id);
}
