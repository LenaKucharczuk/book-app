package ncdc.task.bookapp.domain;

public record FieldValidationError(
    String fieldName,
    String errorCode
) {
}
