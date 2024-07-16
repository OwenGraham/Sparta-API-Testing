package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class GetSingleCourseStepDefs extends StepDefSuper{
    private final SharedState sharedState;
    private int ID;

    public GetSingleCourseStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the response body should contain the info for the {string} course")
    public void theResponseBodyShouldContainTheInfoForTheTECHCourse(String courseName) {
        MatcherAssert.assertThat(sharedState.response.getBody().jsonPath().get("name"), Matchers.is(courseName));
    }

    //Similar to the step def in CommonStepDefs but takes string param from Gherkin script
    @And("the id path param set as {string}")
    public void theIdPathParamSetAs(String invalidID) {
        sharedState.pathParams.put("id",invalidID);
    }
}
