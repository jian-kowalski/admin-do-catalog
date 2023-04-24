package com.jiankowalski.admin.catalogo.domain.exceptions;

import com.jiankowalski.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {
    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(Error anError) {
        return new DomainException(anError.message(), List.of(anError));
    }

    public static DomainException with(List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
