package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Single question from StackOverFlow")
public class Question {

    /**
     * Date in {@link Long} format
     */
    @JsonProperty("date")
    private Long date;

    /**
     * Title of question
     */
    @JsonProperty("title")
    private String title;

    /**
     * Author's profile name
     */
    @JsonProperty("authorName")
    private String authorName;

    /**
     * Link to author profile
     */
    @JsonProperty("authorProfile")
    private String authorProfile;

    /**
     * Is question answered
     */
    @JsonProperty("answered")
    private boolean isAnswered;

    /**
     * Link to question
     */
    @JsonProperty("link")
    private String link;
}
