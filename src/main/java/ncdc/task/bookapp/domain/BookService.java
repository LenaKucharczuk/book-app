package ncdc.task.bookapp.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return List.of(
            new Book("Płatki na wietrze", "Lenix Anix", "ISBN")
        );
    }

    public Book createBook(Book book) {
        return null;
    }
}
