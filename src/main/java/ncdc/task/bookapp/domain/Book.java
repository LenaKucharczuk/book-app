package ncdc.task.bookapp.domain;

import ru.lanwen.verbalregex.VerbalExpression;

import static ncdc.task.bookapp.domain.Validation.assuring;
import static ncdc.task.bookapp.domain.Validation.satisfies;
import static org.springframework.util.ObjectUtils.isEmpty;

public record Book(
    String title,
    String author,
    String isbn
) {
    public Book {
        assuring(
            satisfies("title", "Cannot be empty", !isEmpty(title)),
            satisfies("author", "Cannot be empty", !isEmpty(author)),
            satisfies("isbn", "Cannot be empty", !isEmpty(isbn)),
            satisfies("author", "Either forename or surname must start with letter 'A'", VerbalExpression.regex().then("A").word().build().test(author))
        );
    }
}
