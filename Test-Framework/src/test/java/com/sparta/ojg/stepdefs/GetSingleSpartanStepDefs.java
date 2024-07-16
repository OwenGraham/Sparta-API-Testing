package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class GetSingleSpartanStepDefs {
    private final SharedState sharedState;

    public GetSingleSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the response body should contain information about {string}")
    public void theResponseBodyShouldContainInformationAbout(String expectedName){
        String name = sharedState.response.getBody().jsonPath().get("firstName") + " " + sharedState.response.getBody().jsonPath().get("lastName");
        MatcherAssert.assertThat(name, Matchers.is(expectedName));
    }
}
