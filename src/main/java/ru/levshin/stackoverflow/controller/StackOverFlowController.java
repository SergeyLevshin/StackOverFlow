package ru.levshin.stackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levshin.stackoverflow.model.Question;
import ru.levshin.stackoverflow.service.StackOverFlowSearchService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class StackOverFlowController {

    private final StackOverFlowSearchService stackOverFlowSearchService;

    @GetMapping
    public ResponseEntity<List<Question>> getAnswers(@RequestParam String search) {
        return ResponseEntity.ok(stackOverFlowSearchService.getQuestions(search));
    }

    @GetMapping("/group")
    public ResponseEntity<Map<String, List<Question>>> getAnswersGrouped(@RequestParam String search) {
        return ResponseEntity.ok(stackOverFlowSearchService.getQuestionsGrouped(search));
    }
}
