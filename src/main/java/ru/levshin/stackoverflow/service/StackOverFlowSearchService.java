package ru.levshin.stackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.model.QuestionResponce;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.levshin.stackoverflow.util.Converter.convertToQuestion;

@Service
@RequiredArgsConstructor
public class StackOverFlowSearchService {

    private final Environment environment;

    private final RestTemplate restTemplate;

    public List<Question> getQuestions(String param) {
        QuestionResponce response = restTemplate
                .getForObject(environment.getProperty("baseurl") + param, QuestionResponce.class);
        return response != null
                ? convertToQuestion(response)
                : Collections.emptyList();
    }

    public Map<String, List<Question>> getQuestionsGrouped(String param) {
        return getQuestions(param).stream()
                .collect(Collectors.groupingBy(question -> getString(question.isAnswered())));
    }

    private String getString(boolean isAnswered) {
        return isAnswered ? "Answered" : "Not Answered";
    }
}
