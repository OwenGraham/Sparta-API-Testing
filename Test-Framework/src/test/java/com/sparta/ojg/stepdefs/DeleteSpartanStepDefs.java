package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteSpartanStepDefs{
    private final SharedState sharedState;
    private final Utils utils;

    public DeleteSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        utils = new Utils(sharedState);
    }

    @Then("the status code of the response body from a subsequent GET request to the get Spartan with {int} endpoint should be {int}")
    public void theStatusCodeOfTheResponseBodyFromASubsequentGETRequestToTheGetSpartanWithIdEndpointShouldBe(int id, int expectedStatusCode) {
        //Set the shared state's endpoint field to the endpoint for getting a specific Spartan
        sharedState.endpoint = "/api/Spartans/{id}";
        //Set the request's id path param to the id given in the Gherkin script
        sharedState.pathParams.put("id", String.valueOf(id));
        //Send a DELETE request
        Response response = utils.deleteRequest();
        //Assert that the status code matches the one given in the Gherkin script
        assertThat(response.statusCode(),is(expectedStatusCode));
    }
}
