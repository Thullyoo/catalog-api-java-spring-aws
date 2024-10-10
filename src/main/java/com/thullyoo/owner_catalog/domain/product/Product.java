package com.thullyoo.owner_catalog.domain.product;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.owner.Owner;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
