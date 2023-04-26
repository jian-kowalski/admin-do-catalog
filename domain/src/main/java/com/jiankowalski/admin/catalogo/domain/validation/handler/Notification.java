package com.jiankowalski.admin.catalogo.domain.validation.handler;

import com.jiankowalski.admin.catalogo.domain.exceptions.DomainException;
import com.jiankowalski.admin.catalogo.domain.validation.Error;
import com.jiankowalski.admin.catalogo.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
    private final List<Error> errors;

    private Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error anError) {
        return new Notification(new ArrayList<>()).append(anError);
    }

    public static Notification create(final Throwable t) {
        return create(new Error(t.getMessage()));
    }

    @Override
    public Notification append(Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(ValidationHandler anHandler) {
        this.errors.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public Notification validate(Validation aValidation) {
        try {
            aValidation.validate();
        } catch (DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (Throwable ex) {
            this.errors.add(new Error(ex.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }


}
