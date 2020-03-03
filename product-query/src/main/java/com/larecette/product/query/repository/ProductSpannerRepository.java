package com.larecette.product.query.repository;

import com.larecette.product.query.model.ProductSpanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSpannerRepository extends JpaRepository<ProductSpanner, UUID> {
}
