package com.thullyoo.owner_catalog.controllers;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.services.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> register(@RequestBody @Valid CategoryDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.register(dto));
    }

    @GetMapping
    public ResponseEntity<List<Category>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
