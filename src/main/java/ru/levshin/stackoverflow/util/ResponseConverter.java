package ru.levshin.stackoverflow.util;

import org.springframework.stereotype.Component;
import ru.levshin.stackoverflow.model.Item;
import ru.levshin.stackoverflow.model.QuestionResponse;
import ru.levshin.stackoverflow.model.Question;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides method converting  {@link QuestionResponse} to {@link List} of {@link Question}
 */
@Component
public class ResponseConverter {

    public List<Question> convertToQuestion(QuestionResponse questionResponse) {
        return questionResponse.getItems()
                .stream().map(this::convertItemToQuestion)
                .collect(Collectors.toList());
    }

    private Question convertItemToQuestion(Item item) {
        return Question.builder()
                .date(item.getDate())
                .isAnswered(item.isAnswered())
                .title(item.getTitle())
                .authorName(item.getOwner().getDisplayName())
                .authorProfile(item.getOwner().getLink())
                .link(item.getLink())
                .build();
    }
}
