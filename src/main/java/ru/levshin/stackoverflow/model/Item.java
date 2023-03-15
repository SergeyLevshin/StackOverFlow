package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Item {

    private Owner owner;

    @JsonProperty("last_activity_date")
    private Date date;

    @JsonProperty("is_answered")
    private boolean isAnswered;

    @JsonProperty("link")
    private String link;

    @JsonProperty("title")
    private String title;
}
