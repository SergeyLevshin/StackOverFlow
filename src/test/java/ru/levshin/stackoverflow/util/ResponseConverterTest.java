package ru.levshin.stackoverflow.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.model.QuestionResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ResponseConverterTest {

    @Autowired
    private ResponseConverter converter;

    @Test
    public void convertToQuestion() throws IOException {
        assertEquals(getQuestion(), converter.convertToQuestion(getQuestionResponse()));
    }

    @Test
    public void convertToQuestionEmpty() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setItems(emptyList());
        assertTrue(converter.convertToQuestion(questionResponse).isEmpty());
    }

    private QuestionResponse getQuestionResponse() throws IOException {
        return new ObjectMapper()
                .readValue(Files.readString(
                        Paths.get("src/test/resources/__files/soresponse.json")), QuestionResponse.class);
    }

    private List<Question> getQuestion() throws IOException {
        return Arrays.stream(new ObjectMapper()
                .readValue(Files.readString(
                        Paths.get("src/test/resources/__files/apiresponse.json")), Question[].class))
                .collect(Collectors.toList());
    }
}