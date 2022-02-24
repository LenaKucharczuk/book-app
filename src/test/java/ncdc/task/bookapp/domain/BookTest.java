package ncdc.task.bookapp.domain;

import ncdc.task.bookapp.domain.validation.FieldValidationError;
import ncdc.task.bookapp.domain.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {
    @ParameterizedTest
    @ValueSource(strings = {"Anne Rice", "Rice Anne", "Joan Anne Rosewood", "Anne Joan Rosewood", "Joan Rosewood Anne"})
    public void whenValidBookIsCreated_thenNoExceptionIsThrown(String author) {
        assertDoesNotThrow(() ->
            new Book("Title", author, "Isbn")
        );
    }

    @Test
    public void whenRequiredFieldsAreEmpty_thenExceptionIsThrown() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
            new Book("", "", "")
        );
        assertThat(exception.validationErrors).containsExactlyInAnyOrder(
            new FieldValidationError("title", "Cannot be empty"),
            new FieldValidationError("author", "Cannot be empty"),
            new FieldValidationError("author", "Either forename or surname must start with letter 'A'"),
            new FieldValidationError("isbn", "Cannot be empty")
        );
    }

    @Test
    public void whenRequiredFieldsAreNull_thenExceptionIsThrown() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
            new Book(null, null, null)
        );
        assertThat(exception.validationErrors).containsExactlyInAnyOrder(
            new FieldValidationError("title", "Cannot be empty"),
            new FieldValidationError("author", "Cannot be empty"),
            new FieldValidationError("author", "Either forename or surname must start with letter 'A'"),
            new FieldValidationError("isbn", "Cannot be empty")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Lenix Benix", "LenAs Bena", "LenA Bena", "Lena A"})
    public void whenAuthorsNameNorSurnameStartsWithLetterA_thenExceptionIsThrown(String author) {
        ValidationException exception = assertThrows(ValidationException.class, () ->
            new Book("Title", author, "Isbn"));
        assertThat(exception.validationErrors).containsExactly(
            new FieldValidationError("author", "Either forename or surname must start with letter 'A'")
        );
    }
}