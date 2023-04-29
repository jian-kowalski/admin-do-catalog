package com.jiankowalski.admin.catalogo.application.category.retrieve.list;

import com.jiankowalski.admin.catalogo.domain.Pagination;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.CategorySearchQuery;

import java.util.Objects;

public class DefaultListCategoryUseCase extends ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(CategorySearchQuery aQuery) {
        return categoryGateway.findAll(aQuery)
                .map(CategoryListOutput::from);
    }

}
