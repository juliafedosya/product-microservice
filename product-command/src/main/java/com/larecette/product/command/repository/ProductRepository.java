package com.larecette.product.command.repository;

import com.larecette.product.command.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
