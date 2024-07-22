package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateSpartanStepDefs{
    private final SharedState sharedState;
    public Utils utils;
    public String actual;

    public UpdateSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        utils = new Utils(sharedState);
    }

    @Then("the {string} of the response body from a subsequent GET request to the get Spartan with {int} endpoint should be {string}")
    public void theFieldOfTheResponseBodyFromASubsequentGETRequestToTheGetAllSpartanWithIdEndpointShouldBeTrue(String field, int id, String updated) {
        //Set the shared state's endpoint field as the endpoint for getting a single Spartan by their ID
        sharedState.endpoint = "/api/Spartans/{id}";
        //Set the shared state's id path param as the id given in the Gherkin script
        sharedState.pathParams.put("id",String.valueOf(id));
        //Get the Spartan with the given ID
        Response response = utils.getRequest();

        //Get the value for the field given in the Gherkin script
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        actual = jsonPath.getString(field);
        //Assert that the value is the same as the one given in the Gherkin script
        assertThat(actual,is(updated));
    }
}
