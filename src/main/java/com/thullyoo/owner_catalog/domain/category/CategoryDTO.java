package com.thullyoo.owner_catalog.domain.category;


import jakarta.validation.constraints.NotNull;

public record CategoryDTO(@NotNull(message = "A descrição é obrigatório")String description,
                          @NotNull(message = "O nome é obrigatório")String name,
                          @NotNull(message = "O OwnerId é obrigatório") String ownerId) {
}
