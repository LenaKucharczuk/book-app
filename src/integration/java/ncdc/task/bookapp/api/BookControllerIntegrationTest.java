package ncdc.task.bookapp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server_port=8080")
class BookControllerIntegrationTest {

    @Value("${server_port}")
    private int port;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @Test
    void whenAddingValidBook_thenItIsSuccessfullySaved() throws Exception {
        HttpResponse<String> creationResponse = httpClient
            .send(
                HttpRequest.newBuilder()
                    .header(CONTENT_TYPE, "application/json")
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .POST(
                        ofString(
                            """
                                {
                                    "title": "Title",
                                    "author": "Lenix Aix",
                                    "isbn": "ISBN"
                                }"""
                        )
                    )
                    .build(), HttpResponse.BodyHandlers.ofString()
            );
        assertEquals(creationResponse.statusCode(), HttpStatus.OK.value());

        HttpResponse<String> getAllResponse = httpClient
            .send(
                HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString()
            );
        assertEquals(getAllResponse.statusCode(), HttpStatus.OK.value());
        assertEquals(getAllResponse.body(), """
            [{"title":"Title","author":"Lenix Aix","isbn":"ISBN"}]"""
        );
    }

    @Test
    void whenTryingToAddInvalidBook_thenReturnValidationErrors() throws Exception {
        HttpResponse<String> response = httpClient
            .send(
                HttpRequest.newBuilder()
                    .header(CONTENT_TYPE, "application/json")
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .POST(
                        ofString(
                            """
                                {
                                    "title": "Title",
                                    "author": "Lenix",
                                    "isbn": "ISBN"
                                }"""
                        )
                    )
                    .build(), HttpResponse.BodyHandlers.ofString()
            );
        assertEquals(response.statusCode(), HttpStatus.BAD_REQUEST.value());

        HttpResponse<String> getAllResponse = httpClient
            .send(
                HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString()
            );
        assertEquals(getAllResponse.statusCode(), HttpStatus.OK.value());
        assertEquals(getAllResponse.body(), "[]");
    }
}