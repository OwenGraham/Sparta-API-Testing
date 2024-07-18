package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteSpartanStepDefs extends StepDefSuper{
    private final SharedState sharedState;

    public DeleteSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Then("the status code of the response body from a subsequent GET request to the get Spartan with {int} endpoint should be {int}")
    public void theStatusCodeOfTheResponseBodyFromASubsequentGETRequestToTheGetSpartanWithIdEndpointShouldBe(int id, int expectedStatusCode) {
        sharedState.endpoint = "/api/Spartans/{id}";
        sharedState.pathParams.put("id", String.valueOf(id));
        Utils utils = new Utils(sharedState);
        Response response = utils.deleteRequest();

        assertThat(response.statusCode(),is(expectedStatusCode));
    }
}
