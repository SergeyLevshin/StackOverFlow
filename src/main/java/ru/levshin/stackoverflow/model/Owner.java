package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Owner {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("link")
    private String link;
}
