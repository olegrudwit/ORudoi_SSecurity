package com.orudoi.spring_security.controller;

import com.orudoi.spring_security.model.Product;
import com.orudoi.spring_security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository products;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> all = products.getAll();
        System.out.println(all);

        return all != null
                ? new ResponseEntity<>(all, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getByID(@PathVariable String id) {
        Product byId = products.getById(Long.parseLong(id));
        //!!!
        System.out.println(byId);

        return byId != null
                ? new ResponseEntity<>(byId, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        return product != null && products.add(product)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return products.delete(Long.parseLong(id))
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
