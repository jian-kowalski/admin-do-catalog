package com.jiankowalski.admin.catalogo.infrastructure.api.controllers;

import com.jiankowalski.admin.catalogo.application.category.create.CreateCategoryCommand;
import com.jiankowalski.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.jiankowalski.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.jiankowalski.admin.catalogo.domain.Pagination;
import com.jiankowalski.admin.catalogo.domain.validation.handler.Notification;
import com.jiankowalski.admin.catalogo.infrastructure.api.CategoryAPI;
import com.jiankowalski.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }


    @Override
    public ResponseEntity<?> createCategory(CreateCategoryApiInput input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);
        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;
        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
