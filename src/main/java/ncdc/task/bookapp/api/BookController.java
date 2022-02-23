package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookDto;
import ncdc.task.bookapp.domain.BookService;
import ncdc.task.bookapp.domain.validation.FieldValidationError;
import ncdc.task.bookapp.domain.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
        bookService.createBook(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Set<FieldValidationError>> handleValidationException(ValidationException e) {
        return ResponseEntity.badRequest().body(e.validationErrors);
    }
}
