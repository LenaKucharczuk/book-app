package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WebController.class)
class WebControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenError() throws Exception {
        mvc
            .perform(
                post("/add")
                    .param("title", "Title")
                    .param("author", "Lenix Benix")
                    .param("isbn", "ISBN")
            )
            .andExpect(view().name("add-book"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasFieldErrorCode("bookDto", "author", "Either forename or surname must start with letter 'A'"));
    }

    @Test
    void whenSuccess() throws Exception {
        mvc
            .perform(
                post("/add")
                    .param("title", "Title")
                    .param("author", "Lenix Anix")
                    .param("isbn", "ISBN")
            )
            .andExpect(model().hasNoErrors())
            .andExpect(redirectedUrl("/"));
    }
}
