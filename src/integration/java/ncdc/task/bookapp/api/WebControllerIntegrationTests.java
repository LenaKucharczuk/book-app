package ncdc.task.bookapp.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server_port=8080")
class WebControllerIntegrationTests {

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @Test
    void whenError() throws Exception {
        HttpResponse<String> response = httpClient
            .send(
                HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .uri(new URI("http://localhost:8080/add"))
                    .POST(
                        getEncodedParams(
                            Map.of(
                                "title", "Title",
                                "author", "Lenix Benix",
                                "isbn", "ISBN"
                            )
                        )
                    )
                    .build(), HttpResponse.BodyHandlers.ofString()
            );

        response.body();
    }


    private HttpRequest.BodyPublisher getEncodedParams(Map<String, String> params) {
        return ofString(
            params.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(params.get(key), UTF_8))
                .collect(joining("&", "", ""))
        );
    }
}
