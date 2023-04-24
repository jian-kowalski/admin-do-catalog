package com.jiankowalski.admin.catalogo.domain.validation;


public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(final ValidationHandler anHandler) {
        this.handler = anHandler;
    }

    protected ValidationHandler validationHandler() {
        return handler;
    }

    public abstract void validate();


}
