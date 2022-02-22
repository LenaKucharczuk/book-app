package ncdc.task.bookapp.api;

import ncdc.task.bookapp.AnyWordStartsWithLetterA;
import ncdc.task.bookapp.domain.Book;

import javax.validation.constraints.NotBlank;

public record BookDto(
    @NotBlank
    String title,
    @NotBlank
    @AnyWordStartsWithLetterA(message = "Either forename or surname must start with letter 'A'")
    String author,
    @NotBlank
    String isbn
) {
    public Book toDomain() {
        return new Book(title, author, isbn);
    }

    public static BookDto fromDomain(Book book) {
        return new BookDto(book.title(), book.author(), book.isbn());
    }
}