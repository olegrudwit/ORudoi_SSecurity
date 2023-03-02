package com.orudoi.spring_security.controller;

import com.orudoi.spring_security.model.Product;
import com.orudoi.spring_security.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository products = new ProductRepository();

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        final List<Product> all = products.getAll();
        return all != null && all.isEmpty()
                ? new ResponseEntity<>(all, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"id"})
    public ResponseEntity<Product> getByID(@PathVariable String id) {
        final Product byId = products.getById(Long.parseLong(id));
        return byId != null
                ? new ResponseEntity<>(byId, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping({"id"})
    public ResponseEntity<?> delete(@PathVariable String id) {
        products.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
