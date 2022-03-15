package ncdc.task.bookapp.infractructure;

import ncdc.task.bookapp.domain.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class PersistentBook {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
    private String isbn;

    public PersistentBook(
        String title,
        String author,
        String isbn
    ) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public PersistentBook() {
    }

    public static PersistentBook fromDomain(Book book) {
        return new PersistentBook(book.title(), book.author(), book.isbn());
    }

    public Book toDomain() {
        return new Book(title, author, isbn);
    }
}
