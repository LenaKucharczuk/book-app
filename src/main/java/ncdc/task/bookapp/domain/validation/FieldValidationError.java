package ncdc.task.bookapp.domain.validation;

public record FieldValidationError(
    String fieldName,
    String errorCode
) {
}
