package ncdc.task.bookapp.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Validation {

    @SafeVarargs
    public static void assuring(Optional<FieldValidationError>... maybeValidationErrors) {
        List<FieldValidationError> validationErrors =
            Arrays.stream(maybeValidationErrors)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    public static Optional<FieldValidationError> satisfies(String fieldName, String errorCode, boolean isSatisfied) {
        return isSatisfied ?
            Optional.empty() :
            Optional.of(new FieldValidationError(fieldName, errorCode));
    }
}
