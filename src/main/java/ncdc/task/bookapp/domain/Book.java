package ncdc.task.bookapp.domain;

import ru.lanwen.verbalregex.VerbalExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.springframework.util.ObjectUtils.isEmpty;

public record Book(
    String title,
    String author,
    String isbn
) {
    public Book {
        List<FieldValidationError> validationErrors = getValidationErrors(title, author, isbn);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    private List<FieldValidationError> getValidationErrors(String title, String author, String isbn) {
        return Stream.of(
            validateTitle(title),
            validateAuthor(author),
            validateIsbn(isbn)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private List<FieldValidationError> validateTitle(String title) {
        return isEmpty(title) ?
            List.of(new FieldValidationError("title", "NotEmpty", "Title cannot be empty")) :
            emptyList();
    }

    private List<FieldValidationError> validateIsbn(String isbn) {
        return isEmpty(isbn) ?
            List.of(new FieldValidationError("isbn", "NotEmpty", "ISBN cannot be empty")) :
            emptyList();
    }

    private List<FieldValidationError> validateAuthor(String author) {
        List<FieldValidationError> errors = new ArrayList<>();
        if (isEmpty(author)) {
            errors.add(new FieldValidationError("author", "NotEmpty", "Author cannot be empty"));
        }
        boolean anyWordStartsWithLetterA = VerbalExpression.regex().then("A").word().build().test(author);
        if (!anyWordStartsWithLetterA) {
            errors.add(
                new FieldValidationError(
                    "author",
                    "AnyWordStartsWithLetterA",
                    "Either forename or surname must start with letter 'A'"
                )
            );
        }
        return errors;
    }
}
