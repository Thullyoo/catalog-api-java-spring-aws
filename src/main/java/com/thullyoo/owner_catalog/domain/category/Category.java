package com.thullyoo.owner_catalog.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thullyoo.owner_catalog.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ownerId;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    public Category(CategoryDTO dto){
        this.description = dto.description();
        this.name = dto.name();
        this.ownerId = dto.ownerId();
    }
}
