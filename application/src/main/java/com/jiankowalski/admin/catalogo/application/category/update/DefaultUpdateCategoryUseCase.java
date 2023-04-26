package com.jiankowalski.admin.catalogo.application.category.update;

import com.jiankowalski.admin.catalogo.domain.category.Category;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.CategoryID;
import com.jiankowalski.admin.catalogo.domain.exceptions.DomainException;
import com.jiankowalski.admin.catalogo.domain.validation.Error;
import com.jiankowalski.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;


public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {
    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var aCategory = this.categoryGateway.findById(CategoryID.from(aCommand.id()))
                .orElseThrow(notFound(aCommand));
        final var notification = Notification.create();
        aCategory
                .update(aCommand.name(), aCommand.description(), aCommand.isActive())
                .validate(notification);

        return notification.hasError() ? Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private static Supplier<DomainException> notFound(UpdateCategoryCommand aCommand) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(aCommand.id())));
    }
}
