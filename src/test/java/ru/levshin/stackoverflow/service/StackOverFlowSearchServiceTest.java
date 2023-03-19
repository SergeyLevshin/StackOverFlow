package ru.levshin.stackoverflow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import ru.levshin.stackoverflow.model.GroupedSoResponse;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.model.QuestionResponse;
import ru.levshin.stackoverflow.util.ResponseConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class StackOverFlowSearchServiceTest {

    private final String PARAM = randomAlphabetic(5);

    private QuestionResponse questionResponse;

    @Mock
    private Environment environment;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseConverter converter;

    @BeforeEach
    public void init() {
        String url = "http://google.com";//some valid url
        when(environment.getProperty(anyString())).thenReturn(url);
    }

    @InjectMocks
    private StackOverFlowSearchService stackOverFlowSearchService;

    @Test
    public void getItemsThrowRestClientException() {
        when(restTemplate.getForObject(anyString(), eq(QuestionResponse.class)))
                .thenThrow(RestClientResponseException.class);

        assertThrows(RestClientResponseException.class, () ->stackOverFlowSearchService.getItems(PARAM));
    }

    @Test
    public void getItemsWithEmptyResponse() {
        when(restTemplate.getForObject(anyString(), eq(QuestionResponse.class)))
                .thenReturn(questionResponse);

        assertTrue(stackOverFlowSearchService.getItems(PARAM).isEmpty());
    }

    @Test
    public void getItems() throws IOException {
        List<Question> expectedQuestions = Lists.list(createQuestion(), createQuestion());
        QuestionResponse questionResponse = new ObjectMapper()
                .readValue(Files.readString(
                        Paths.get("src/test/resources/__files/soresponse.json")), QuestionResponse.class);
        when(restTemplate.getForObject(anyString(), eq(QuestionResponse.class)))
                .thenReturn(questionResponse);
        when(converter.convertToQuestion(questionResponse)).thenReturn(expectedQuestions);

        assertEquals(expectedQuestions, stackOverFlowSearchService.getItems(PARAM));
    }

    @Test
    public void getGroupedResponse() throws IOException {
        Question question1 = createQuestion();
        question1.setAnswered(true);
        Question question2 = createQuestion();
        question2.setAnswered(false);
        List<Question> expectedQuestions = Lists.list(question1, question2);
        QuestionResponse questionResponse = new ObjectMapper()
                .readValue(Files.readString(
                        Paths.get("src/test/resources/__files/soresponse.json")), QuestionResponse.class);
        when(restTemplate.getForObject(anyString(), eq(QuestionResponse.class)))
                .thenReturn(questionResponse);
        when(converter.convertToQuestion(questionResponse)).thenReturn(expectedQuestions);

        GroupedSoResponse groupedResponse = stackOverFlowSearchService.getGroupedResponse(PARAM);

        assertNotNull(groupedResponse.getAnswered());
        assertNotNull(groupedResponse.getNotAnswered());
        assertEquals(question1, groupedResponse.getAnswered().get(0));
        assertEquals(question2, groupedResponse.getNotAnswered().get(0));
    }

    private Question createQuestion() {
        return Question.builder()
                .authorName(randomAlphabetic(10))
                .authorProfile(randomAlphabetic(30))
                .date(nextLong())
                .link(randomAlphabetic(30))
                .isAnswered(nextBoolean())
                .build();
    }
}