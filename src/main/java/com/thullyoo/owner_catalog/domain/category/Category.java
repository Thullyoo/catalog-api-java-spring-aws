package com.thullyoo.owner_catalog.domain.category;

import com.thullyoo.owner_catalog.domain.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TB_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
