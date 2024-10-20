package com.thullyoo.owner_catalog.domain.product;

public record ProductUpRequestDTO(String name, Double price, Long productId, String description, String ownerId) {
}
