package com.jiankowalski.admin.catalogo.application.category.retrieve.list;

import com.jiankowalski.admin.catalogo.application.UseCase;
import com.jiankowalski.admin.catalogo.domain.Pagination;
import com.jiankowalski.admin.catalogo.domain.category.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
