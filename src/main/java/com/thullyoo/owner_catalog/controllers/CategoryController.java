package com.thullyoo.owner_catalog.controllers;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "CRUD for categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Save product", description = "Method to register a product with details and image")
    @ApiResponse(responseCode = "201", description = "Category saved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    public ResponseEntity<Category> register(@RequestBody @Valid CategoryDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.register(dto));
    }

    @GetMapping
    @Operation(summary = "List all categories", description = "Method to retrieve the list of all registered categories")
    @ApiResponse(responseCode = "200", description = "Successfully listed all categories")
    public ResponseEntity<List<Category>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.list());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Method to update a category's details based on its ID")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Method to delete a category based on its ID")
    @ApiResponse(responseCode = "204", description = "Category successfully deleted")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
