package com.thullyoo.owner_catalog.controllers;


import com.thullyoo.owner_catalog.domain.product.Product;
import com.thullyoo.owner_catalog.domain.product.ProductDTO;
import com.thullyoo.owner_catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Product> register(@RequestParam("name") String name,
                                            @RequestParam("price") double price,
                                            @RequestParam("description") String description,
                                            @RequestParam("categoryId") Long categoryId,
                                            @RequestParam("ownerId") String ownerId,
                                            @RequestParam("image") MultipartFile image){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.register(new ProductDTO(name,price,categoryId,description,ownerId), image));
    }

    @GetMapping
    public ResponseEntity<List<Product>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
