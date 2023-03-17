package ru.levshin.stackoverflow.model;

import lombok.Data;

import java.util.List;

/**
 * Used to simplify mapping StackOverFlow API response
 */
@Data
public class QuestionResponse {

    private List<Item> items;
}
