package com.jiankowalski.admin.catalogo.application.category.retrieve.list;

import com.jiankowalski.admin.catalogo.domain.category.Category;

import java.time.Instant;

public record CategoryListOutput(
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant deletedAt
) {
    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt());
    }

}
