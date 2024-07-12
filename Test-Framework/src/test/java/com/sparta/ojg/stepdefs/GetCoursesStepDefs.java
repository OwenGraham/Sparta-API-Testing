package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.Map;

public class GetCoursesStepDefs extends StepDefSuper{
    private final SharedState sharedState;

    public GetCoursesStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Given("I have obtained a bearer token")
    public void IHaveObtainedABearerToken() throws JsonProcessingException {
        String body = mapper.writeValueAsString(Map.of(
                "username","sparta",
                "password","global"
        ));
        sharedState.response = RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .contentType("application/json")
                .body(body)
                .when()
                .post()
                .thenReturn();
        sharedState.token = response.getBody().jsonPath().getString("token");
    }

    @When("I send a GET request")
    public void iSendAGETRequest() {
        sharedState.response = RestAssured
                .given()
                    .baseUri(sharedState.ROOT_URI)
                    .basePath(sharedState.endpoint)
                .when()
                    .get()
                .thenReturn();
    }
}
