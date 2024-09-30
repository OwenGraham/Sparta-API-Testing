package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetSingleCourseStepDefs{
    private final SharedState sharedState;
    public String name;

    public GetSingleCourseStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the response body should contain the info for the {string} course")
    public void theResponseBodyShouldContainTheInfoForTheTECHCourse(String courseName) {
        //Get the name value from the response body
        String responseBody = sharedState.response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        name = jsonPath.get("name");
        //Assert that the name matches the course name given in the Gherkin script
        assertThat(name, is(courseName));
    }

    //Similar to the step def in CommonStepDefs but takes string param from Gherkin script
    @And("the id path param set as {string}")
    public void theIdPathParamSetAs(String invalidID) {
        sharedState.pathParams.put("id",invalidID);
    }
}
