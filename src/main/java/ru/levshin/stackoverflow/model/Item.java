package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents the json element from StackOverFlow API response
 */
@Data
public class Item {

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("last_activity_date")
    private Long date;

    @JsonProperty("is_answered")
    private boolean isAnswered;

    @JsonProperty("link")
    private String link;

    @JsonProperty("title")
    private String title;
}
