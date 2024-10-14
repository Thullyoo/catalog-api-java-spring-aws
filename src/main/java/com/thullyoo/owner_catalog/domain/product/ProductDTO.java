package com.thullyoo.owner_catalog.domain.product;

import jakarta.validation.constraints.NotNull;

public record ProductDTO(@NotNull(message = "A descrição é obrigatório") String name,
                         @NotNull(message = "A descrição é obrigatório")Double price,
                         @NotNull(message = "A descrição é obrigatório")Long categoryId,
                         @NotNull(message = "A descrição é obrigatório")String description,
                         @NotNull(message = "O ownerId é obrigatório")String ownerId
) {
}
