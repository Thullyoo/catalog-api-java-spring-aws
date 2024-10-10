package com.thullyoo.owner_catalog.services;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.product.Product;
import com.thullyoo.owner_catalog.domain.product.ProductDTO;
import com.thullyoo.owner_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product register(ProductDTO dto){
        Product product = new Product(dto);

        Optional<Category> category = categoryService.findById(dto.categoryId());

        if (category.isEmpty()){
            throw new RuntimeException("Categoria não encontrada");
        }

        product.setCategory(category.get());

        productRepository.save(product);

        return product;
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Product update(Long id, ProductDTO dto){

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new RuntimeException("Produto não cadastrado");
        }
        if(!dto.name().isEmpty()){
            product.get().setName(dto.name());
        }
        if(dto.price() != null){
            product.get().setPrice(dto.price());
        }
        if(dto.categoryId() != null){
            Optional<Category> category = categoryService.findById(dto.categoryId());

            if (category.isEmpty()){
                throw new RuntimeException("Categoria não encontrada");
            }

            product.get().setCategory(category.get());
        }

        productRepository.save(product.get());

        return product.get();
    }

    public void delete(Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new RuntimeException("Produto não cadastrado");
        }

        productRepository.delete(product.get());
    }
}
