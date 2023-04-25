package com.jiankowalski.admin.catalogo.application.category.create;

import com.jiankowalski.admin.catalogo.domain.category.Category;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aCategory = Category.newCategory(aCommand.name(), aCommand.descrition(), aCommand.isActive());
        aCategory.validate(new ThrowsValidationHandler());
        this.categoryGateway.create(aCategory);
        return CreateCategoryOutput.from(aCategory);
    }
}
