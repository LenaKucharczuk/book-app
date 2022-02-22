package ncdc.task.bookapp.domain;

import ru.lanwen.verbalregex.VerbalExpression;

import static ncdc.task.bookapp.domain.validation.Validation.assuring;
import static ncdc.task.bookapp.domain.validation.Validation.isNotEmpty;
import static ncdc.task.bookapp.domain.validation.Validation.satisfies;

public record Book(
    String title,
    String author,
    String isbn
) {
    public Book {
        assuring(
            isNotEmpty("title", title),
            isNotEmpty("author", author),
            isNotEmpty("isbn", isbn),
            satisfies("author", "Either forename or surname must start with letter 'A'",
                VerbalExpression.regex().then("A").word().build().test(author)
            )
        );
    }
}
