package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response with lists of questions grouped by answered/not answered
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedSoResponse {

    @JsonProperty("answered")
    private List<Question> answered;

    @JsonProperty("notAnswered")
    private List<Question> notAnswered;
}
