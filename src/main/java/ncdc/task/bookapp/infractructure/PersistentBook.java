package ncdc.task.bookapp.infractructure;

import ncdc.task.bookapp.domain.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String isbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PersistentBook) obj;
        return Objects.equals(this.title, that.title) &&
            Objects.equals(this.author, that.author) &&
            Objects.equals(this.isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, isbn);
    }

    @Override
    public String toString() {
        return "PersistentBook[" +
            "title=" + title + ", " +
            "author=" + author + ", " +
            "isbn=" + isbn + ']';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
