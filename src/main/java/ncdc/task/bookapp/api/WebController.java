package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookService;
import ncdc.task.bookapp.domain.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class WebController {
    private final String HOME_MAPPING = "/";

    private final BookService bookService;

    public WebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(HOME_MAPPING)
    public String showBookListPage(Model model) {
        List<BookDto> books = bookService.getAllBooks().stream().map(BookDto::fromDomain).collect(toList());
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/add")
    public String showAddBookPage(BookDto book) {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(BookDto book, BindingResult result, Model model) {
        try {
            bookService.createBook(book.toDomain());
        } catch (ValidationException e) {
            e.validationErrors.forEach(it ->
                result.rejectValue(it.fieldName(), it.errorCode(), it.errorCode())
            );
            return "add-book";
        }
        return "redirect:" + HOME_MAPPING;
    }
}
