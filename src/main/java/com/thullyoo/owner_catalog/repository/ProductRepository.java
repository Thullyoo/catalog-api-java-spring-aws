package com.thullyoo.owner_catalog.repository;

import com.thullyoo.owner_catalog.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
