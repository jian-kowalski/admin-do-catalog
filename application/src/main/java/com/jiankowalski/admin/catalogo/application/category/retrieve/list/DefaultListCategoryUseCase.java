package com.jiankowalski.admin.catalogo.application.category.retrieve.list;

import com.jiankowalski.admin.catalogo.domain.Pagination;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.SearchQuery;

import java.util.Objects;

public class DefaultListCategoryUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(SearchQuery aQuery) {
        return categoryGateway.findAll(aQuery)
                .map(CategoryListOutput::from);
    }

}
