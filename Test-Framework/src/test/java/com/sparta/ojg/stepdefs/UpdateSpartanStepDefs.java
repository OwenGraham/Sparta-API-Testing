package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateSpartanStepDefs extends StepDefSuper{
    private final SharedState sharedState;

    public UpdateSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Then("the {string} of the response body from a subsequent GET request to the get Spartan with {int} endpoint should be {string}")
    public void theFieldOfTheResponseBodyFromASubsequentGETRequestToTheGetAllSpartanWithIdEndpointShouldBeTrue(String field, int id, String updated) {
        sharedState.endpoint = "/api/Spartans/{id}";
        sharedState.pathParams.put("id",String.valueOf(id));
        Utils utils = new Utils(sharedState);
        Response response = utils.getRequest();

        assertThat(response.getBody().jsonPath().get(field).toString(),is(updated));
    }
}
