package ncdc.task.bookapp.domain.validation;

import java.util.Set;

public class ValidationException extends IllegalArgumentException {
    public Set<FieldValidationError> validationErrors;

    public ValidationException(Set<FieldValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}

