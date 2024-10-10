package com.thullyoo.owner_catalog.domain.owner;

import com.thullyoo.owner_catalog.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_OWNER")
@AllArgsConstructor
@NoArgsConstructor
public class Owner{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_owner")
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @OneToMany(mappedBy = "owner")
    private List<Product> products;

}
