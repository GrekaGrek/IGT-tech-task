package com.igt.technical.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AbstractIntegrationTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;

    private static final WireMockServer wireMockServer = new WireMockServer(options().port(8120));

    @BeforeAll
    static void init() {
        TomcatURLStreamHandlerFactory.getInstance();
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    protected WebTestClient.ResponseSpec makePostRequest(String path, Object body) {
        return webTestClient.post()
                .uri("/api/" + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange();
    }

    protected WebTestClient.ResponseSpec makeGetRequest(String path) {
        return webTestClient.post()
                .uri("/api/" + path)
                .exchange();
    }

    protected WebTestClient.ResponseSpec makePutRequest(String path, Object body) {
        return webTestClient.post()
                .uri("/api/" + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange();
    }

    protected WebTestClient.ResponseSpec makeDeleteRequest(String path) {
        return webTestClient.post()
                .uri("/api/" + path)
                .exchange();
    }
}
