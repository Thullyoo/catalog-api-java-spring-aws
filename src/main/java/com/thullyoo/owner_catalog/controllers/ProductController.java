package com.thullyoo.owner_catalog.controllers;


import com.thullyoo.owner_catalog.domain.product.Product;
import com.thullyoo.owner_catalog.domain.product.ProductDTO;
import com.thullyoo.owner_catalog.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "CRUD for products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Save product", description = "Method to register a product with details and image")
    @ApiResponse(responseCode = "201", description = "Product saved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    @ApiResponse(responseCode = "400", description = "Category and Product have different owners")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "500", description = "Error uploading the image")
    public ResponseEntity<Product> register(@RequestParam("name") String name,
                                            @RequestParam("price") double price,
                                            @RequestParam("description") String description,
                                            @RequestParam("categoryId") Long categoryId,
                                            @RequestParam("ownerId") String ownerId,
                                            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.register(new ProductDTO(name, price, categoryId, description, ownerId), image));
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Method to retrieve the list of all registered products")
    @ApiResponse(responseCode = "200", description = "Successfully listed all products")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.list());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Method to update a product's details based on its ID")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Method to delete a product based on its ID")
    @ApiResponse(responseCode = "204", description = "Product successfully deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
