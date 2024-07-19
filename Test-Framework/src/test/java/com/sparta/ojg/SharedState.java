package com.sparta.ojg;

import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SharedState {
    //Live version
//    public final String ROOT_URI = "https://spartaacademyapi20240530152521.azurewebsites.net";
    //Containerized version
    public final String ROOT_URI = "http://localhost:8080";

    //The path used in the API call
    public String endpoint;

    public Map<String,String> pathParams = new HashMap<>();
    public Map<String,String> headers = new HashMap<>();
    public String requestBody = "";
    public File bodyFile;

    public Response response;

    public Account account = new Account();
}
