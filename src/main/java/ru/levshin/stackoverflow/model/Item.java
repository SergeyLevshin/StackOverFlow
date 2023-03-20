package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Represents the json element from StackOverFlow API response
 */
@Data
@ApiModel(description = "Shortened representation of element in incoming response")
public class Item {

    /**
     * Question owner {@link Owner}
     */
    @JsonProperty("owner")
    private Owner owner;

    /**
     * Last activity date in {@link Long} format
     */
    @JsonProperty("last_activity_date")
    private Long date;

    /**
     * Is question answered
     */
    @JsonProperty("is_answered")
    private boolean isAnswered;

    /**
     * Link to question
     */
    @JsonProperty("link")
    private String link;

    /**
     * Title of question
     */
    @JsonProperty("title")
    private String title;
}
