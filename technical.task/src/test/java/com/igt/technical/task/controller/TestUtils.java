package com.igt.technical.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

abstract class TestUtils {

    private static final String TEST_DATA_PATH = "src/test/resources/testdata";

    private static ObjectMapper objectMapper = new ObjectMapper();

    static String getContentFromFile(String path) throws IOException {
        File file = new File(TEST_DATA_PATH + path);
        JsonNode jsonNode = objectMapper.readTree(file);
        return objectMapper.writeValueAsString(jsonNode);
    }
}
