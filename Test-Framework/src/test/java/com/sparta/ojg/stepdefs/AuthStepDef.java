package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import java.util.Map;

public class AuthStepDef extends StepDefSuper{
    private String username;
    private String password;

    private final SharedState sharedState;

    public AuthStepDef(SharedState sharedState) {
        this.sharedState = sharedState;
    }


    @Given("the endpoint {string}")
    public void theEndpointEndpoint(String endpoint) {
        sharedState.endpoint = endpoint;
    }

    @And("the username {string}")
    public void theUsernameUsername(String username) {
        this.username = username;
    }


    @And("the password {string}")
    public void thePasswordPassword(String password) {
        this.password = password;
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() throws JsonProcessingException {
        String body = mapper.writeValueAsString(Map.of(
                "username",username,
                "password",password
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
    }

    @Then("the status code of the response should be {string}")
    public void theStatusCodeOfTheResponseShouldBe(String code) {
        MatcherAssert.assertThat(sharedState.response.statusCode(), Matchers.is(Integer.valueOf(code)));
    }

    @And("the response body should include a bearer token")
    public void theResponseBodyShouldIncludeABearerToken() {
        MatcherAssert.assertThat(sharedState.response.getBody().jsonPath().get("token"),Matchers.notNullValue());
    }

    @And("the response body should not include a bearer token")
    public void theResponseBodyShouldNotIncludeABearerToken() {
        MatcherAssert.assertThat(sharedState.response.getBody().jsonPath().get("token"),Matchers.not(Matchers.notNullValue()));
    }
}
