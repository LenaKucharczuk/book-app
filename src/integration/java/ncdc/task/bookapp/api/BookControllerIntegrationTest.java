package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate httpClient;

    @Test
    void whenAddingValidBook_thenItIsSuccessfullySaved() {
        ResponseEntity<String> creationResponse = httpClient
            .postForEntity("/books", new BookDto("Title", "Lenix Aix", "ISBN"), String.class);
        assertEquals(creationResponse.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<BookDto>> getAllResponse = httpClient
            .exchange("/books", GET, EMPTY, new ParameterizedTypeReference<>() {
            });
        assertEquals(getAllResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(getAllResponse.getBody(), List.of(new BookDto("Title", "Lenix Aix", "ISBN")));
    }

    @Test
    void whenTryingToAddInvalidBook_thenReturnValidationErrors() {
        ResponseEntity<String> creationResponse = httpClient
            .postForEntity("/books", new BookDto("Title", "Lenix", "ISBN"), String.class);
        assertEquals(creationResponse.getStatusCode(), HttpStatus.BAD_REQUEST);

        ResponseEntity<List<BookDto>> getAllResponse = httpClient
            .exchange("/books", GET, EMPTY, new ParameterizedTypeReference<>() {
            });
        assertEquals(getAllResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(getAllResponse.getBody(), emptyList());
    }
}