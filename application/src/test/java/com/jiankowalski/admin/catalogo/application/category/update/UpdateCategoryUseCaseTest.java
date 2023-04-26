package com.jiankowalski.admin.catalogo.application.category.update;

import com.jiankowalski.admin.catalogo.domain.category.Category;
import com.jiankowalski.admin.catalogo.domain.category.CategoryGateway;
import com.jiankowalski.admin.catalogo.domain.category.CategoryID;
import com.jiankowalski.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(categoryGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory = Category.newCategory("Filme", null, true);
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();
        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive);
        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        verify(categoryGateway, times(1)).findById(eq(expectedId));
        verify(categoryGateway, times(1))
                .update(argThat(aUpdatedCategory ->
                        Objects.equals(aUpdatedCategory.getName(), expectedName)
                                && Objects.equals(aUpdatedCategory.getDescription(), expectedDescription)
                                && Objects.equals(aUpdatedCategory.isActive(), expectedIsActive)
                                && Objects.equals(aUpdatedCategory.getId(), expectedId)
                                && Objects.equals(aUpdatedCategory.getCreatedAt(), aCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                                && Objects.nonNull(aUpdatedCategory.getUpdatedAt())
                                && Objects.isNull(aUpdatedCategory.getDeletedAt())
                                && Objects.nonNull(aUpdatedCategory.getId())
                ));
    }

    @Test
    public void givenAInvalidName_whenCallsUpdateCategory_shouldReturnDomainException() {
        final var aCategory = Category.newCategory("Filme", null, true);
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand =
                UpdateCategoryCommand.with(expectedId.getValue(), expectedName, expectedDescription, expectedIsActive);
        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));
        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.getFirstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(categoryGateway, times(0)).update(any());
    }

    @Test
    public void givenAValidInactivateCommand_whenCallsUpdateCategory_shouldReturnInactivedCategoryId() {
        final var aCategory = Category.newCategory("Filme", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var expectedId = aCategory.getId();
        final var aCommand = UpdateCategoryCommand
                .with(expectedId.getValue(), expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        verify(categoryGateway, times(1)).findById(eq(expectedId));
        verify(categoryGateway, times(1))
                .update(argThat(aUpdatedCategory ->
                        Objects.equals(aUpdatedCategory.getName(), expectedName)
                                && Objects.equals(aUpdatedCategory.getDescription(), expectedDescription)
                                && Objects.equals(aUpdatedCategory.isActive(), expectedIsActive)
                                && Objects.equals(aUpdatedCategory.getId(), expectedId)
                                && Objects.equals(aUpdatedCategory.getCreatedAt(), aCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                                && Objects.nonNull(aUpdatedCategory.getUpdatedAt())
                                && Objects.nonNull(aUpdatedCategory.getDeletedAt())
                                && Objects.nonNull(aUpdatedCategory.getId())
                ));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnException() {
        final var aCategory = Category.newCategory("Filme", null, true);
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(expectedId.getValue(),
                expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(categoryGateway, times(1))
                .update(argThat(aUpdatedCategory ->
                        Objects.equals(aUpdatedCategory.getName(), expectedName)
                                && Objects.equals(aUpdatedCategory.getDescription(), expectedDescription)
                                && Objects.equals(aUpdatedCategory.isActive(), expectedIsActive)
                                && Objects.equals(aUpdatedCategory.getId(), expectedId)
                                && Objects.equals(aUpdatedCategory.getCreatedAt(), aCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                                && Objects.nonNull(aUpdatedCategory.getUpdatedAt())
                                && Objects.isNull(aUpdatedCategory.getDeletedAt())
                                && Objects.nonNull(aUpdatedCategory.getId())
                ));
    }

    @Test
    public void givenACommandWithInvalidId_whenCallsUpdateCategory_shouldReturnNotFoundException() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var expectedId = "123";
        final var expectedErroCount = 1;
        final var expectedErroMessage = "Category with ID 123 was not found";
        final var aCommand = UpdateCategoryCommand
                .with(expectedId, expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.findById(eq(CategoryID.from(expectedId))))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErroMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErroCount, actualException.getErrors().size());

        verify(categoryGateway, times(1)).findById(eq(CategoryID.from(expectedId)));
        verify(categoryGateway, times(0)).update(any());
    }
}
