package com.jiankowalski.admin.catalogo.domain.category;

import com.jiankowalski.admin.catalogo.domain.exceptions.DomainException;
import com.jiankowalski.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNull(atualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(null, "Filmes", true);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }


    @Test
    public void givenAnInvalidEmptyName_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(" ", "Filmes", true);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory("Fi ", "Filmes", true);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedName = """
                    Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes
                    Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes
                    Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes
                    Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes Filmes              \s
                """;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(expectedName, "Filmes", true);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedIsActive = true;
        final var expectedDescription = "   ";
        final var expectedName = "Filmes";
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidFalseIsActive_whenCallCategoryAndValidate_thenShouldReturnError() {
        final var expectedIsActive = false;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedName = "Filmes";
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }
}
