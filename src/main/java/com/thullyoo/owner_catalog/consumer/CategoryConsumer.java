package com.thullyoo.owner_catalog.consumer;

import com.thullyoo.owner_catalog.config.rabbitMQ.RabbitMQConfig;
import com.thullyoo.owner_catalog.domain.category.CategoryDTO;
import com.thullyoo.owner_catalog.domain.category.CategoryDelRequestDTO;
import com.thullyoo.owner_catalog.domain.category.CategoryUpRequestDTO;
import com.thullyoo.owner_catalog.services.CategoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryConsumer {

    @Autowired
    private CategoryService categoryService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_1)
    public void registerCategory(CategoryDTO dto) {
        System.out.println("Mensagem para registrar a categoria: " + dto.toString());
        categoryService.register(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_2)
    public void updateCategory(CategoryUpRequestDTO dto) {
        System.out.println("Mensagem para atualizar category: " + dto.toString());
        categoryService.update(dto.id(), new CategoryDTO(dto.description(),dto.name(), dto.ownerId()));
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_METHOD_3)
    public void deleteCategory(CategoryDelRequestDTO dto) {
        System.out.println("Mensagem para deletar Category: " + dto.toString());
        categoryService.delete(dto.categoryId());
    }
}
