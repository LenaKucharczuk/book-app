package ncdc.task.bookapp;

import javax.validation.constraints.NotBlank;

public record BookDTO(
        @NotBlank
        String title,
        @NotBlank
        @AnyWordStartsWithLetterA(message = "Either forename or surname must start with letter 'A'")
        String author,
        @NotBlank
        String isbn
) {
}