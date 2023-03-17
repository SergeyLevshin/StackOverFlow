package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO model, represents single item from StackOverFlow API response
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @JsonProperty("date")
    private Long date;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authorName")
    private String authorName;

    @JsonProperty("authorProfile")
    private String authorProfile;

    @JsonProperty("answered")
    private boolean isAnswered;

    @JsonProperty("link")
    private String link;
}
