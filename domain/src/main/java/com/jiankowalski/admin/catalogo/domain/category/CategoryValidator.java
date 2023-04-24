package com.jiankowalski.admin.catalogo.domain.category;

import com.jiankowalski.admin.catalogo.domain.validation.Error;
import com.jiankowalski.admin.catalogo.domain.validation.ValidationHandler;
import com.jiankowalski.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {
    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler anHandler) {
        super(anHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if (category.getName() == null || category.getName().isBlank()) {
            validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
