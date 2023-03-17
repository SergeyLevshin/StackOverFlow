package ru.levshin.stackoverflow.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.levshin.stackoverflow.model.GroupedSoResponse;
import ru.levshin.stackoverflow.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "stackoverflowurl=http://localhost:8089/search")
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class WireMockTests {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        wireMockServer = new WireMockServer(options()
                .extensions(new ResponseTemplateTransformer(false))
                .port(8089));
        wireMockServer.stubFor(get(urlPathMatching("/search"))
                .willReturn(aResponse().withBodyFile("soresponse.json")
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @AfterEach
    public void clear() {
        wireMockServer.stop();
    }

    @Test
    void getItemsSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                .queryParam("param", "java")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getContent()))
                .andReturn();
    }

    @Test
    void getItemsGroupedSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/search/group")
                .queryParam("param", "java")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getContentAsMap()))
                .andReturn();
    }

    @Test
    void getItemsMissedParam() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void getItemsServiceUnavailable() throws Exception {
        wireMockServer.stop();

        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                .queryParam("param", "java")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("The service is unavailable, try again later"))
                .andReturn();
    }

    private String getContent() throws IOException {
        return objectMapper.writeValueAsString(
                objectMapper.readValue(
                        Files.readString(
                                Paths.get("src/test/resources/__files/apiresponse.json")), Question[].class));
    }

    private String getContentAsMap() throws IOException {
        return objectMapper.writeValueAsString(
                objectMapper.readValue(
                        Files.readString(
                                Paths.get("src/test/resources/__files/soGroupResponse.json")),
                        GroupedSoResponse.class));
    }
}
