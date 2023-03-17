package ru.levshin.stackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levshin.stackoverflow.model.GroupedSoResponse;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.service.StackOverFlowSearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class StackOverFlowController {

    private final StackOverFlowSearchService stackOverFlowSearchService;

    /**
     * Simple search for questions on https://stackoverflow.com/questions by title
     * @param param desired search parameter
     * @return {@link List} of questions
     */
    @GetMapping
    public ResponseEntity<List<Question>> getAnswers(@RequestParam String param) {
        return ResponseEntity.ok(stackOverFlowSearchService.getItems(param));
    }

    /**
     * Search for questions on https://stackoverflow.com/questions by title.
     * The result is grouped by answered/not answered
     * @param param desired search parameter
     * @return {@link GroupedSoResponse} with questions
     */
    @GetMapping("/group")
    public ResponseEntity<GroupedSoResponse> getAnswersGrouped(@RequestParam String param) {
        return ResponseEntity.ok(stackOverFlowSearchService.getGroupedResponse(param));
    }
}
