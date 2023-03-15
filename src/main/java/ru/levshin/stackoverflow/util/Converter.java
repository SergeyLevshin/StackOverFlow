package ru.levshin.stackoverflow.util;

import lombok.experimental.UtilityClass;
import ru.levshin.stackoverflow.model.Item;
import ru.levshin.stackoverflow.model.QuestionResponce;
import ru.levshin.stackoverflow.model.Question;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class Converter {

    public static List<Question> convertToQuestion(QuestionResponce questionResponce) {
        return questionResponce.getItems()
                .stream().map(Converter::convertItemToQuestiuon)
                .collect(Collectors.toList());
    }

    private static Question convertItemToQuestiuon(Item item) {
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
