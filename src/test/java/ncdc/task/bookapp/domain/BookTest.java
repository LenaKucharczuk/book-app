package ncdc.task.bookapp.domain;

import ncdc.task.bookapp.domain.validation.FieldValidationError;
import ncdc.task.bookapp.domain.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {
    @Test
    public void whenValidBookIsCreated_thenNoExceptionIsThrown() {
        assertDoesNotThrow(() ->
            new Book("Title", "Anne Rice", "Isbn")
        );
        assertDoesNotThrow(() ->
            new Book("Title", "Rice Anne", "Isbn")
        );
        assertDoesNotThrow(() ->
            new Book("Title", "Joan Anne Rosewood", "Isbn")
        );
        assertDoesNotThrow(() ->
            new Book("Title", "Anne Joan Rosewood", "Isbn")
        );
        assertDoesNotThrow(() ->
            new Book("Title", "Joan Rosewood Anne", "Isbn")
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

    @Test
    public void whenAuthorsNameNorSurnameStartsWithLetterA_thenExceptionIsThrown() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Book("Title", "Lenix Benix", "Isbn");
        });
        assertThat(exception.validationErrors).containsExactly(
            new FieldValidationError("author", "Either forename or surname must start with letter 'A'")
        );
    }
}