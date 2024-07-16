package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;

public class CreateSpartanStepDefs extends StepDefSuper{
    private final SharedState sharedState;

    public CreateSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the request body containing valid data taken from the file {string}")
    public void theRequestBodyContainingValidDataTakenFromTheFile(String path) {
        sharedState.bodyFile = new File(path);
    }

    @And("the data in the response body should match the data provided in the request from the file {string}")
    public void theDataInTheResponseBodyShouldMatchTheDataProvidedInTheRequestFromTheFile(String path) throws IOException {
        File expected = new File(path);
        JsonNode expectedJson = mapper.readTree(expected);
        JsonNode actualJson = mapper.readTree(sharedState.response.getBody().toString());

        assertThat(actualJson,equalTo(expectedJson));
    }

    @And("the request body containing invalid data taken from the file {string}")
    public void theRequestBodyContainingInvalidDataTakenFromTheFile(String path) {
        sharedState.bodyFile = new File(path);
    }
}
