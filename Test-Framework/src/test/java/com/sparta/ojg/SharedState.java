package com.sparta.ojg;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;

public class SharedState {
    public final String ROOT_URI = "http://localhost:8080";
    public String endpoint;
    public String token;
    public Response response;
    ObjectMapper mapper = new ObjectMapper();
    JSONParser jsonParser = new JSONParser();
}
