package com.example.productCatelogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productCatelogue.entity.Product;
import com.example.productCatelogue.repo.ProductRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    // Add a new product
    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product save = this.productRepo.save(product);
        return ResponseEntity.ok(save);
    }

    // Get all products
    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = this.productRepo.findAll();
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Optional<Product> product = this.productRepo.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Product newProduct) {
        Optional<Product> optionalProduct = this.productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            // Update other fields as needed
            Product updatedProduct = this.productRepo.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
