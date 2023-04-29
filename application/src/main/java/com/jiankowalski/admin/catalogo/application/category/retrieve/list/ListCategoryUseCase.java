package com.jiankowalski.admin.catalogo.application.category.retrieve.list;

import com.jiankowalski.admin.catalogo.application.UseCase;
import com.jiankowalski.admin.catalogo.domain.Pagination;
import com.jiankowalski.admin.catalogo.domain.category.CategorySearchQuery;

public abstract class ListCategoryUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
