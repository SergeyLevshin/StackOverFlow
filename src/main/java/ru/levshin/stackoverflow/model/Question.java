package ru.levshin.stackoverflow.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Question {

    private Date date;

    private String title;

    private String authorName;

    private String authorProfile;

    private boolean isAnswered;

    private String link;
}
