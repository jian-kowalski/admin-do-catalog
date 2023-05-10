package com.jiankowalski.admin.catalogo.application.category.create;

import com.jiankowalski.admin.catalogo.domain.category.Category;

public record CreateCategoryOutput(String id) {
    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId().getValue());
    }

    public static CreateCategoryOutput from(final String anId) {
        return new CreateCategoryOutput(anId);
    }

}
