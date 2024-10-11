package com.thullyoo.owner_catalog.services;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category register(CategoryDTO dto){

        if (dto.description() == null){
            throw new RuntimeException("Descrição não pode ser null");
        }

        Category category = new Category(dto);
        categoryRepository.save(category);

        return category;
    }

    public List<Category> list(){
        return categoryRepository.findAll();
    }

    public Category update(Long id, CategoryDTO dto){

        Optional<Category> optionalCategory = findById(id);

        if (optionalCategory.isEmpty()){
            throw new RuntimeException("Categoria não encontrada");
        }

        if(dto.description() != ""){
            optionalCategory.get().setDescription(dto.description());
        }

        if(dto.name() != ""){
            optionalCategory.get().setName(dto.name());
        }

        categoryRepository.save(optionalCategory.get());

        return optionalCategory.get();
    }

    public void delete(Long id){

        Optional<Category> optionalCategory = findById(id);

        if (optionalCategory.isEmpty()){
            throw new RuntimeException("Categoria não encontrada");
        }

        categoryRepository.delete(optionalCategory.get());
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
}
