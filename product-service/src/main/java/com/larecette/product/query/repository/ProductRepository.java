package com.larecette.product.query.repository;


import com.larecette.product.query.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
