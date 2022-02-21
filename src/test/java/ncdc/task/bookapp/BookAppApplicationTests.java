package ncdc.task.bookapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WebController.class)
class BookAppApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void whenError() throws Exception {
        mvc.perform(post("/add")
                .param("title", "Title")
                .param("author", "Lenix Benix")
                .param("isbn", "ISBN")
        )
                .andExpect(view().name("add-book"))
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("book", "author", "Pattern"));
    }

    @Test
    void whenSuccess() throws Exception {
        mvc.perform(post("/add")
                .param("title", "Title")
                .param("author", "Lenix Anix")
                .param("isbn", "ISBN")
        )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/"));
    }
}
