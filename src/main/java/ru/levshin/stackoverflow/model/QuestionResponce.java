package ru.levshin.stackoverflow.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponce {

    private List<Item> items;
}
