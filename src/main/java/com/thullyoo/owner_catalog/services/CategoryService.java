package com.thullyoo.owner_catalog.services;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.exceptions.CategoryNotExistsException;
import com.thullyoo.owner_catalog.repository.CategoryRepository;
import com.thullyoo.owner_catalog.services.aws.MessageDTO;
import com.thullyoo.owner_catalog.services.aws.SNSService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SNSService snsService;

    @Transactional
    public Category register(CategoryDTO dto){

        Category category = new Category(dto);
        categoryRepository.save(category);

        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }


    public List<Category> list(){
        return categoryRepository.findAll();
    }

    @Transactional
    public Category update(Long id, CategoryDTO dto){

        Optional<Category> optionalCategory = findById(id);

        if (optionalCategory.isEmpty()){
            throw new CategoryNotExistsException("Categoria não encontrada");
        }

        if(dto.description() != ""){
            optionalCategory.get().setDescription(dto.description());
        }

        if(dto.name() != ""){
            optionalCategory.get().setName(dto.name());
        }

        snsService.publish(new MessageDTO(optionalCategory.get().toString()));

        categoryRepository.save(optionalCategory.get());

        return optionalCategory.get();
    }

    @Transactional
    public void delete(Long id){

        Optional<Category> optionalCategory = findById(id);

        if (optionalCategory.isEmpty()){
            throw new CategoryNotExistsException("Categoria não encontrada");
        }


        this.snsService.publish(new MessageDTO(optionalCategory.get().deleteToString()));

        categoryRepository.delete(optionalCategory.get());


    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
}
