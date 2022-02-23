package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void whenTryingToAddInvalidBook_thenReturnValidationErrors() throws Exception {
        mvc
            .perform(
                post("/add")
                    .param("title", "")
                    .param("author", "Lenix Benix")
                    .param("isbn", "ISBN")
            )
            .andExpect(view().name("add-book"))
            .andExpect(model().errorCount(2))
            .andExpect(model().attributeHasFieldErrors("bookDto", "author", "title"));

        mvc
            .perform(get("/"))
            .andExpect(
                model().attribute("books", emptyList())
            );
    }

    @Test
    void whenAddingValidBook_thenItIsSuccessfullySaved() throws Exception {
        mvc
            .perform(
                post("/add")
                    .param("title", "Title")
                    .param("author", "Lenix Anix")
                    .param("isbn", "ISBN")
            )
            .andExpect(model().hasNoErrors())
            .andExpect(redirectedUrl("/"));

        mvc
            .perform(get("/"))
            .andExpect(
                model().attribute("books", List.of(new BookDto("Title", "Lenix Anix", "ISBN")))
            );
    }
}
