package ncdc.task.bookapp.api;

import ncdc.task.bookapp.domain.BookDto;
import ncdc.task.bookapp.domain.validation.FieldValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

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
            .exchange("/books", GET, EMPTY, new ParameterizedTypeReference<>() {});
        assertEquals(getAllResponse.getStatusCode(), HttpStatus.OK);
        assertThat(getAllResponse.getBody())
            .containsExactly(new BookDto("Title", "Lenix Aix", "ISBN"));
    }

    @Test
    void whenTryingToAddInvalidBook_thenReturnValidationErrors() {
        ResponseEntity<List<FieldValidationError>> creationResponse = httpClient
            .exchange("/books", POST, new HttpEntity<>(new BookDto("Title", "Lenix", "ISBN")), new ParameterizedTypeReference<>() {});
        assertEquals(creationResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertThat(creationResponse.getBody()).hasSize(1);

        ResponseEntity<List<BookDto>> getAllResponse = httpClient
            .exchange("/books", GET, EMPTY, new ParameterizedTypeReference<>() {});
        assertEquals(getAllResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(getAllResponse.getBody(), emptyList());
    }
}