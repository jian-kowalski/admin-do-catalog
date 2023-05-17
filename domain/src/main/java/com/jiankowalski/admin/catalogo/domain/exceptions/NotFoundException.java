package com.jiankowalski.admin.catalogo.domain.exceptions;

import com.jiankowalski.admin.catalogo.domain.AggregateRoot;
import com.jiankowalski.admin.catalogo.domain.Identifier;
import com.jiankowalski.admin.catalogo.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {
    private NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(anAggregate.getSimpleName(), id.getValue());
        return new NotFoundException(anError, Collections.emptyList());
    }
}
