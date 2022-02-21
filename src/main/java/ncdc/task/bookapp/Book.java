package ncdc.task.bookapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record Book(
        @NotBlank
        String title,
        @NotBlank
        @Pattern(
                regexp = "[\\w ]*[A\\w]+[ \\w]*",
                message = "Either forename or surname must start with letter 'A'"
        )
        String author,
        @NotBlank
        String isbn
) {
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
