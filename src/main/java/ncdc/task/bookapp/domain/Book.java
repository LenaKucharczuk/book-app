package ncdc.task.bookapp.domain;

public record Book(
    String title,
    String author,
    String isbn
) {
}