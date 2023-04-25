package com.jiankowalski.admin.catalogo.application.category.create;

import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

public class CreateCategoryUseCaseTest {

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
        final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());
        final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);
        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.categoryID());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory -> {
                    return Objects.equals(aCategory.getName(), expectedName)
                            && Objects.equals(aCategory.getDescription(), expectedDescription)
                            && Objects.equals(aCategory.isActive(), expectedIsActive)
                            && Objects.nonNull(aCategory.getCreatedAt())
                            && Objects.nonNull(aCategory.getUpdatedAt())
                            && Objects.isNull(aCategory.getDeletedAt())
                            && Objects.nonNull(aCategory.getId());
                }));
    }

}
