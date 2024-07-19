package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonStepDefs{
    private final SharedState sharedState;
    public Utils utils;

    public CommonStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        utils = new Utils(sharedState);
    }

    @Given("the endpoint {string}")
    public void theEndpointEndpoint(String endpoint) {
        //Set the shared state's endpoint field to the string given in the Gherkin script
        sharedState.endpoint = endpoint;
    }

    @Given("I have obtained a bearer token")
    public void IHaveObtainedABearerToken() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //Set the shared state's body as the JSON string corresponding to the valid username "sparta" and password "global"
        sharedState.requestBody = mapper.writeValueAsString(Map.of(
                "username","sparta",
                "password","global"
        ));
        //Set the shared state's endpoint field to the endpoint for obtaining a bearer token
        sharedState.endpoint = "/Auth/login";
        //Send POST request
        sharedState.response = utils.postRequest();
        //Get the token from the response
        String responseBody = sharedState.response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String token = jsonPath.get("token");
        //Add the token to the shared state's Authorization headers
        sharedState.headers.put("Authorization","Bearer " + token);
    }

    @Given("I have not obtained a bearer token")
    public void iHaveNotObtainedABearerToken() {
        //Remove the Authorization header from the shared state's headers
        sharedState.headers.remove("Authorization");
    }

    @And("the id path param set as {int}")
    public void theIdPathParamSetAs(int id){
        //Set the id path param as the number given in the Gherkin script
        sharedState.pathParams.put("id", String.valueOf(id));
    }

    @When("I send a GET request")
    public void iSendAGETRequest() {
        //Send a GET request
        sharedState.response = utils.getRequest();
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() {
        //Send a POST request
        sharedState.response = utils.postRequest();
    }

    @When("I send a POST request with body from file")
    public void iSendAPOSTRequestWithBodyFromFile() {
        //Send a POST request with body taken from a file
        sharedState.response = utils.postRequestWithBodyFile();
    }

    @When("I send a PUT request")
    public void iSendAPUTRequest() {
        //Send a PUT request
        sharedState.response = utils.putRequest();
    }

    @When("I send a DELETE request")
    public void iSendADELETERequest() {
        //Send a DELETE request
        sharedState.response = utils.deleteRequest();
    }

    @Then("the status code of the response should be {int}")
    public void theStatusCodeOfTheResponseShouldBe(int code) {
        //Assert that the status code of the response matches the number given by the Gherkin script
        assertThat(sharedState.response.statusCode(), is(code));
    }
}
