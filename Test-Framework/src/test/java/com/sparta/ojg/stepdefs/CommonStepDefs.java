package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Map;

public class CommonStepDefs extends StepDefSuper{
    private static SharedState sharedState;
    private static Utils utils;

    public CommonStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        utils = new Utils(sharedState);
    }

    @Given("the endpoint {string}")
    public void theEndpointEndpoint(String endpoint) {
        sharedState.endpoint = endpoint;
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
        sharedState.response = utils.getRequest();
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() throws JsonProcessingException {
        sharedState.response = utils.postRequest();
    }

    @When("I send a POST request with body from file")
    public void iSendAPOSTRequestWithBodyFromFile() throws JsonProcessingException {
        sharedState.response = utils.postRequestWithBodyFile();
    }

    @When("I send a PUT request")
    public void iSendAPUTRequest() {
        sharedState.response = utils.putRequest();
    }

    @Then("the status code of the response should be {int}")
    public void theStatusCodeOfTheResponseShouldBe(int code) {
        MatcherAssert.assertThat(sharedState.response.statusCode(), Matchers.is(code));
    }

    @When("I send a DELETE request")
    public void iSendADELETERequest() {
        sharedState.response = utils.deleteRequest();
    }
}
