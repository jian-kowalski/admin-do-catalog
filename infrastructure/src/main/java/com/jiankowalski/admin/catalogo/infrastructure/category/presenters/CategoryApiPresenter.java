package com.jiankowalski.admin.catalogo.infrastructure.category.presenters;

import com.jiankowalski.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.jiankowalski.admin.catalogo.infrastructure.category.models.CategoryApiOutput;

import java.util.function.Function;

public interface CategoryApiPresenter {

    Function<CategoryOutput, CategoryApiOutput> present = output ->
            new CategoryApiOutput(
                    output.id().getValue(),
                    output.name(),
                    output.description(),
                    output.isActive(),
                    output.createdAt(),
                    output.updatedAt(),
                    output.deletedAt()
            );
}
