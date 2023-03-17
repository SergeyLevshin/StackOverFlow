package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Question owner from StackOverFlow API response
 */
@Data
public class Owner {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("link")
    private String link;
}
