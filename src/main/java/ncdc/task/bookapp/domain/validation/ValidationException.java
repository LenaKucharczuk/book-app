package ncdc.task.bookapp.domain.validation;

import java.util.List;

public class ValidationException extends IllegalArgumentException {
    public List<FieldValidationError> validationErrors;

    public ValidationException(List<FieldValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
