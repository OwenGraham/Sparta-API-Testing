package com.sparta.ojg.stepdefs;

import groovy.json.JsonParser;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;

public class StepDefSuper {
    public static final String ROOT_URI = "http://localhost:8080";
    String endpoint;
    String token;
    static Response response;
    ObjectMapper mapper = new ObjectMapper();
    JSONParser jsonParser = new JSONParser();
}
