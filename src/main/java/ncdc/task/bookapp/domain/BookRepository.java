package ncdc.task.bookapp.domain;

import java.util.List;

public interface BookRepository {
    void save(Book book);

    List<Book> getAll();
}
