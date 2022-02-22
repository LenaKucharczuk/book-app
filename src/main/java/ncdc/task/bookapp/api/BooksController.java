package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.Book;
import ncdc.task.bookapp.domain.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController("books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto book) {
        Book createdBook = bookService.createBook(book.toDomain());
        return ResponseEntity.ok(BookDto.fromDomain(createdBook));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(
            bookService.getAllBooks()
                .stream()
                .map(BookDto::fromDomain)
                .collect(toList())
        );

    }
}
