package ncdc.task.bookapp.domain;

public record BookDto(
    String title,
    String author,
    String isbn
) {
    public Book toDomain() {
        return new Book(title, author, isbn);
    }

    public static BookDto fromDomain(Book book) {
        return new BookDto(book.title(), book.author(), book.isbn());
    }
}