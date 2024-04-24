package com.example.productCatelogue.repo;

import com.example.productCatelogue.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    
}
