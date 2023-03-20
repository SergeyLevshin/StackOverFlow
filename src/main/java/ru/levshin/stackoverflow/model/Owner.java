package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Question owner from StackOverFlow API response
 */
@Data
@ApiModel(description = "Shortened representation of owner in incoming response")
public class Owner {

    /**
     * Display name
     */
    @JsonProperty("display_name")
    private String displayName;

    /**
     * Link to profile
     */
    @JsonProperty("link")
    private String link;
}
