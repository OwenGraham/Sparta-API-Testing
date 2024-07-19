package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetSingleSpartanStepDefs {
    private final SharedState sharedState;

    public GetSingleSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the response body should contain information about {string}")
    public void theResponseBodyShouldContainInformationAbout(String expectedName){
        //Get the first and last names from the response body
        String responseBody = sharedState.response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String firstName = jsonPath.get("firstName");
        String lastName = jsonPath.get("lastName");
        //Assert that the concatenated first and last name in the response match the full name given in the Gherkin script
        assertThat(firstName + " " + lastName, is(expectedName));
    }
}
