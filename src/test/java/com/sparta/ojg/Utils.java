package com.sparta.ojg;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Utils {
    private SharedState sharedState;

    public Utils(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    public Response getRequest(){
        //Send a GET request using the URI, parameters, and headers of the shared state
        return RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .pathParams(sharedState.pathParams)
                .headers(sharedState.headers)
                .when()
                .get()
                .thenReturn();
    }

    // Common method to send POST request
    private Response postRequest(Object bodyContent) {
        RequestSpecification request = RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .contentType("application/json")
                .headers(sharedState.headers);

        if (bodyContent instanceof File) {
            request.body((File) bodyContent); // Handle body as file
        } else if (bodyContent instanceof String) {
            request.body((String) bodyContent); // Handle body as string
        }

        return request.when().post().thenReturn();
    }

    // Method for posting with request body as string
    public Response postRequest() {
        return postRequest(sharedState.requestBody);
    }

    // Method for posting with body from file
    public Response postRequestWithBodyFile() {
        return postRequest(sharedState.bodyFile);
    }

    public Response putRequest(){
        return RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .contentType("application/json")
                .body(sharedState.bodyFile)
                .headers(sharedState.headers)
                .pathParams(sharedState.pathParams)
                .when()
                .put()
                .thenReturn();
    }

    public Response deleteRequest() {
        return RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .headers(sharedState.headers)
                .pathParams(sharedState.pathParams)
                .when()
                .delete()
                .thenReturn();
    }

    public boolean foundSpartan(int id){
        //Set the shared state's endpoint field to the endpoint to get all Spartans
        sharedState.endpoint = "/api/Spartans";
        //Send a GET request to get all Spartans
        Response response = getRequest();

        // Extract data from the JSON array in the response
        List<Map<String, ?>> users = response.jsonPath().getList("$");

        //Check all Spartans to see if any have ID 0
        boolean found = false;
        for (Map<String, ?> user : users) {
            if (user.get("id").equals(id)) found = true;
        }
        return found;
    }
}
