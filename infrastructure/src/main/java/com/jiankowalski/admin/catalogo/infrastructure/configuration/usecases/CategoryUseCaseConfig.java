package com.jiankowalski.admin.catalogo.infrastructure.configuration.usecases;

import com.jiankowalski.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.create.DefaultCreateCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.delete.DefaultDeleteCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.delete.DeleteCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.jiankowalski.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.jiankowalski.admin.catalogo.application.category.retrieve.list.DefaultListCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.retrieve.list.ListCategoriesUseCase;
import com.jiankowalski.admin.catalogo.application.category.update.DefaultUpdateCategoryUseCase;
import com.jiankowalski.admin.catalogo.application.category.update.UpdateCategoryUseCase;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {
    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoryUseCase() {
        return new DefaultListCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }
}
