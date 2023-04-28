package com.jiankowalski.admin.catalogo.application.category.retrieve.get;

import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.CategoryID;
import com.jiankowalski.admin.catalogo.domain.exceptions.DomainException;
import com.jiankowalski.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIn) {
        final var aCategoryId = CategoryID.from(anIn);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(
                        notFound(aCategoryId)
                );
    }

    private static Supplier<DomainException> notFound(final CategoryID categoryID) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(categoryID.getValue())));
    }
}
