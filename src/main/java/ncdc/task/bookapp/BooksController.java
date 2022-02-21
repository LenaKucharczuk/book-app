package ncdc.task.bookapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("books")
public class BooksController {
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO book) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().build();

    }
}
