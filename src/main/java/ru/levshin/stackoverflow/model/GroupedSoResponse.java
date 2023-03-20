package ru.levshin.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Response grouped by answered/not answered")
public class GroupedSoResponse {

    /**
     * List of answered questions
     */
    @JsonProperty("answered")
    private List<Question> answered;

    /**
     * List of not answered questions
     */
    @JsonProperty("notAnswered")
    private List<Question> notAnswered;
}
