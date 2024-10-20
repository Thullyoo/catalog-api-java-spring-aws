package com.thullyoo.owner_catalog.consumer;

import com.thullyoo.owner_catalog.config.rabbitMQ.RabbitMQConfig;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.domain.category.CategoryDelRequestDTO;
import com.thullyoo.owner_catalog.domain.category.CategoryUpRequestDTO;
import com.thullyoo.owner_catalog.domain.product.ProductDTO;
import com.thullyoo.owner_catalog.domain.product.ProductDelRequestDTO;
import com.thullyoo.owner_catalog.domain.product.ProductUpRequestDTO;
import com.thullyoo.owner_catalog.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_4)
    public void registerProduct(ProductDTO dto) {
        System.out.println("Mensagem para registrar a product: " + dto.toString());
        productService.register(dto, null);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_5)
    public void updateProduct(ProductUpRequestDTO dto) {
        System.out.println("Mensagem para atualizar product: " + dto.toString());
        productService.update(dto.productId(), new ProductDTO(dto.name(), dto.price(), dto.productId(), dto.description(), dto.ownerId()));
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_6)
    public void deleteProduct(ProductDelRequestDTO dto) {
        System.out.println("Mensagem para deletar product: " + dto.toString());
    }

}
