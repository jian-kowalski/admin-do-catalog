package com.jiankowalski.admin.catalogo.application.category.update;

import com.jiankowalski.admin.catalogo.application.UseCase;
import com.jiankowalski.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
