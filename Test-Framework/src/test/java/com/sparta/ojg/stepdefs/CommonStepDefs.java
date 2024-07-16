package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Map;

public class CommonStepDefs extends StepDefSuper{
    private final SharedState sharedState;

    public CommonStepDefs(SharedState sharedState) {
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
                .basePath("/Auth/login")
                .contentType("application/json")
                .body(body)
                .when()
                .post()
                .thenReturn();
        sharedState.headers.put("Authorization","Bearer " + sharedState.response.getBody().jsonPath().getString("token"));
    }

    @Given("I have not obtained a bearer token")
    public void iHaveNotObtainedABearerToken() {
        sharedState.pathParams.remove("Authorization");
    }

    @And("the id path param set as {int}")
    public void theIdPathParamSetAs(int id){
        sharedState.pathParams.put("id", String.valueOf(id));
    }

    @When("I send a GET request")
    public void iSendAGETRequest() {
        sharedState.response = RestAssured
                .given()
                    .baseUri(sharedState.ROOT_URI)
                    .basePath(sharedState.endpoint)
                    .pathParams(sharedState.pathParams)
                    .headers(sharedState.headers)
                .when()
                    .get()
                .thenReturn();
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() throws JsonProcessingException {
        sharedState.response = RestAssured
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

    @When("I send a POST request with body from file")
    public void iSendAPOSTRequestWithBodyFromFile() throws JsonProcessingException {
        sharedState.response = RestAssured
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

    @Then("the status code of the response should be {int}")
    public void theStatusCodeOfTheResponseShouldBe(int code) {
        MatcherAssert.assertThat(sharedState.response.statusCode(), Matchers.is(code));
    }
}
