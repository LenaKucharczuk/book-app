package ncdc.task.bookapp.domain;

import ncdc.task.bookapp.api.BookDto;
import ncdc.task.bookapp.infractructure.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.getAll().stream().map(BookDto::fromDomain).collect(toList());
    }

    public void createBook(BookDto bookDto) {
        Book book = bookDto.toDomain();
        bookRepository.save(book);
    }
}

