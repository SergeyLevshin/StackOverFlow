package ru.levshin.stackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.levshin.stackoverflow.model.GroupedSoResponse;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.model.QuestionResponse;
import ru.levshin.stackoverflow.util.ResponseConverter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StackOverFlowSearchService implements SearchService<Question> {

    private final Environment environment;

    private final RestTemplate restTemplate;

    private final ResponseConverter converter;

    @Override
    public List<Question> getItems(String param) {
        String url = UriComponentsBuilder.fromHttpUrl(String.valueOf(environment.getProperty("stackoverflowurl")))
                .queryParam("site", "stackoverflow")
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("intitle", param)
                .encode().toUriString();
        QuestionResponse response = restTemplate
                .getForObject(url, QuestionResponse.class);
        return response != null
                ? converter.convertToQuestion(response)
                : Collections.emptyList();
    }

    /**
     * Search by parameter and group by answered/not answered
     * @param param parameter of search
     * @return {@link GroupedSoResponse} with questions
     */
    public GroupedSoResponse getGroupedResponse(String param) {
        Map<Boolean, List<Question>> map = getItems(param).stream()
                .collect(Collectors.groupingBy(Question::isAnswered));
        return new GroupedSoResponse(map.get(true), map.get(false));
    }
}
