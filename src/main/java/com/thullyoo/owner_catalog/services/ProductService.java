package com.thullyoo.owner_catalog.services;

import com.thullyoo.owner_catalog.domain.category.Category;
import com.thullyoo.owner_catalog.domain.product.Product;
import com.thullyoo.owner_catalog.domain.product.ProductDTO;
import com.thullyoo.owner_catalog.exceptions.CategoryNotExistsException;
import com.thullyoo.owner_catalog.exceptions.CategoryProductOwnerDiffException;
import com.thullyoo.owner_catalog.exceptions.ProductNotExistsException;
import com.thullyoo.owner_catalog.exceptions.UploadImageException;
import com.thullyoo.owner_catalog.repository.ProductRepository;
import com.thullyoo.owner_catalog.services.aws.MessageDTO;
import com.thullyoo.owner_catalog.services.aws.S3UploadImage;
import com.thullyoo.owner_catalog.services.aws.SNSService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private S3UploadImage s3UploadImage;

    @Autowired
    private SNSService snsService;

    @Transactional
    public Product register(ProductDTO dto, MultipartFile image){

        Product product = new Product(dto);

        Optional<Category> category = categoryService.findById(dto.categoryId());

        if (category.isEmpty()){
            throw new CategoryNotExistsException("Categoria não encontrada");
        }

        if (!category.get().getOwnerId().equals(dto.ownerId())) {
            System.out.println(category.get().getOwnerId());
            System.out.println(dto.ownerId());
            throw new CategoryProductOwnerDiffException("Categoria e Produto de diferentes Proprietários");
        }

        product.setCategory(category.get());

        Product productSave =  productRepository.save(product);

        String imgUrl = s3UploadImage.uploadImage(image, dto.ownerId(), productSave.getId());

        if (!imgUrl.isEmpty()){
            productSave.setImgUrl(imgUrl);
            snsService.publish(new MessageDTO(productSave.toString()));
        } else {
            productSave.setImgUrl(null);
        }

        productRepository.save(productSave);



        return product;
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Product update(Long id, ProductDTO dto){

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotExistsException("Produto não cadastrado");
        }
        if(dto.name() != "") {
            product.get().setName(dto.name());
        }
        if(dto.price() != -1){
            product.get().setPrice(dto.price());
        }
        if (dto.description() != ""){
            product.get().setDescription(dto.description());
        }
        if(dto.categoryId() != null){
            Optional<Category> category = categoryService.findById(dto.categoryId());

            if (category.isEmpty()){
                throw new CategoryNotExistsException("Categoria não encontrada");
            }

            product.get().setCategory(category.get());
        }

        productRepository.save(product.get());

        snsService.publish(new MessageDTO(product.toString()));

        return product.get();
    }

    public void delete(Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotExistsException("Produto não cadastrado");
        }

        productRepository.delete(product.get());

        this.snsService.publish(new MessageDTO(product.get().deleteToString()));
    }
}
