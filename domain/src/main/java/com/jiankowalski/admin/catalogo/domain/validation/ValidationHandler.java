package com.jiankowalski.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    List<Error> getErrors();

    default Error getFirstError() {
        return hasError() ? getErrors().get(0) : null;
    }

    interface Validation {
        void validate();
    }

}
