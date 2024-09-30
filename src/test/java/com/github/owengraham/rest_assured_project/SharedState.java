package com.github.owengraham.rest_assured_project;

import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SharedState {
    public final String ROOT_URI = getURI();

    public String getURI(){
        String os = System.getProperty("os.name").toLowerCase();
        //Live version
        if (os.contains("nix") || os.contains("nux") || os.contains("aix")){
            return "https://spartaacademyapi20240530152521.azurewebsites.net";
        }
        //Containerized version
        else{
            return "http://localhost:8080";
        }
    }

    //The path used in the API call
    public String endpoint;

    public Map<String,String> pathParams = new HashMap<>();
    public Map<String,String> headers = new HashMap<>();
    public String requestBody = "";
    public File bodyFile;

    public Response response;

    public Account account = new Account();
}
