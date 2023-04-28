package com.jiankowalski.admin.catalogo.application.category.delete;

import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.CategoryID;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public void execute(final String anIn) {
        categoryGateway.deleteById(CategoryID.from(anIn));
    }
}
