package com.jiankowalski.admin.catalogo.application.category.create;

import com.jiankowalski.admin.catalogo.application.UseCase;
import com.jiankowalski.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
