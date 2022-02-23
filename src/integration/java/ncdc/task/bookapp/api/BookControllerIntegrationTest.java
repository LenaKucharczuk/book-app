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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server_port=8080")
class BookControllerIntegrationTest {

    @Value("${server_port}")
    int port;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @Test
    void whenSuccess() throws Exception {
        HttpResponse<String> response = httpClient
            .send(
                HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .POST(
                        ofString(
                            """
                                {
                                    "title": "Title",
                                    "author": "Lenix Aix",
                                    "isbn": "ISBN"
                                }
                                """
                        )
                    )
                    .build(), HttpResponse.BodyHandlers.ofString()
            );

        assert response.statusCode() == HttpStatus.OK.value();
    }

    @Test
    void whenError() throws Exception {
        HttpResponse<String> response = httpClient
            .send(
                HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI("http://localhost:" + port + "/books"))
                    .POST(
                        ofString(
                            """
                                {
                                    "title": "Title",
                                    "author": "Lenix",
                                    "isbn": "ISBN"
                                }
                                """
                        )
                    )
                    .build(), HttpResponse.BodyHandlers.ofString()
            );

        assert response.statusCode() == HttpStatus.BAD_REQUEST.value();
    }
}