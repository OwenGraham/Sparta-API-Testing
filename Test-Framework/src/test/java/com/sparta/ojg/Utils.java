package com.sparta.ojg;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Utils {
    private SharedState sharedState;

    public Utils(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    public Response getRequest(){
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

    public Response postRequest(){
        return RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .contentType("application/json")
                .body(sharedState.requestBody)
                .headers(sharedState.headers)
                .when()
                .post()
                .thenReturn();
    }

    public Response postRequestWithBodyFile(){
        return RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .contentType("application/json")
                .body(sharedState.bodyFile)
                .headers(sharedState.headers)
                .when()
                .post()
                .thenReturn();
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
}
