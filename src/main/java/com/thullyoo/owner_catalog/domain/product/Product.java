package com.thullyoo.owner_catalog.domain.product;

import com.thullyoo.owner_catalog.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private String imgUrl;

    @Column(nullable = false)
    private String ownerId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    public Product(ProductDTO dto){
        this.name = dto.name();
        this.price = dto.price();
        this.description = dto.description();
        this.ownerId = dto.ownerId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("price", price);
        json.put("imgUrl", imgUrl);
        json.put("ownerId", ownerId);
        json.put("description", description);
        json.put("category", category);
        json.put("type", "product");
        return json.toString();
    }

    public String deleteToString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("price", price);
        json.put("imgUrl", imgUrl);
        json.put("ownerId", ownerId);
        json.put("description", description);
        json.put("category", category);
        json.put("type", "delete-p");
        return json.toString();
    }
}
