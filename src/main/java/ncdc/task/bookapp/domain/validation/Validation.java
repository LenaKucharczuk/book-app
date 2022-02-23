package ncdc.task.bookapp.domain.validation;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.springframework.util.ObjectUtils.isEmpty;

public class Validation {

    @SafeVarargs
    public static void assuring(Optional<FieldValidationError>... maybeValidationErrors) {
        Set<FieldValidationError> validationErrors =
            Arrays.stream(maybeValidationErrors)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    public static Optional<FieldValidationError> satisfies(String fieldName, String errorCode, boolean isSatisfied) {
        return isSatisfied ?
            Optional.empty() :
            Optional.of(new FieldValidationError(fieldName, errorCode));
    }

    public static Optional<FieldValidationError> isNotEmpty(String fieldName, String value) {
        return satisfies(fieldName, "Cannot be empty", !isEmpty(value));
    }
}
