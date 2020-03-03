package com.larecette.product.query.repository;

import com.larecette.product.query.model.ProductElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.UUID;

public interface ProductElasticRepository extends ElasticsearchCrudRepository<ProductElastic, UUID> {
}
