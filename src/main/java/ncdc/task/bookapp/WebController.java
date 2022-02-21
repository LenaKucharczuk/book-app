package ncdc.task.bookapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {
    private final String HOME_MAPPING = "/";

    @GetMapping(HOME_MAPPING)
    public String showBookListPage(Model model) {
        List<BookDTO> books = List.of(
                new BookDTO("Płatki na wietrze", "Lenix", "ISBN")
        );
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/add")
    public String showAddBookPage(BookDTO book) {

        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookDTO book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        return "redirect:" + HOME_MAPPING;
    }
}
