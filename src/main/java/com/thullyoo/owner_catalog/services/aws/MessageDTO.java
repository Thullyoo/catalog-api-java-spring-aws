package com.thullyoo.owner_catalog.services.aws;

import jakarta.validation.constraints.NotEmpty;

public record MessageDTO(@NotEmpty(message = "A messagem é obrigatória") String message) {
}
