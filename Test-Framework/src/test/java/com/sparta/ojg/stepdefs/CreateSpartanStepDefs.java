package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateSpartanStepDefs{
    private final SharedState sharedState;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Utils utils;

    public CreateSpartanStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        utils = new Utils(sharedState);
    }

    @And("the request body containing data taken from the file {string}")
    public void theRequestBodyContainingDataTakenFromTheFile(String path) {
        //Set the shared state's bodyFile field to the File object created from the path given in the Gherkin script
        sharedState.bodyFile = new File(path);
    }

    @And("the data in the response body should match the data provided in the request from the file {string}")
    public void theDataInTheResponseBodyShouldMatchTheDataProvidedInTheRequestFromTheFile(String path) throws IOException {
        //Create a File object using the path given in the Gherkin script
        File expected = new File(path);
        //Create a JsonNode object from the file
        JsonNode expectedJson = mapper.readTree(expected);
        //Create a JSonNode object from the response body
        JsonNode actualJson = null;
        try {
            actualJson = mapper.readTree(sharedState.response.getBody().toString());
        } catch (JsonParseException e) {
            assertThat("fail here",false);
        }
        //Assert that the response body matches the data in the file
        assertThat(actualJson,equalTo(expectedJson));
    }

    @And("the Spartan should not appear in subsequent GET requests to the get all Spartans endpoint")
    public void theSpartanShouldNotAppearInSubsequentGETRequestsToTheGetAllSpartansEndpoint() {
        //Assert that no Spartan with ID 0 was found
        assertThat(utils.foundSpartan(0),is(false));
    }

    @And("the Spartan should appear in subsequent GET requests to the get all Spartans endpoint")
    public void theSpartanShouldAppearInSubsequentGETRequestsToTheGetAllSpartansEndpoint() {
        //Assert that a Spartan with ID 0 was found
        assertThat(utils.foundSpartan(0),is(true));
    }

    @And("the response should include a {string}")
    public void theResponseShouldIncludeA(String expectedErrorMessage) {
        //Parse the errors in the response body to a Map
        Map<String,List<String>> errors = sharedState.response.jsonPath().getMap("errors");
        boolean found = false;
        //Iterate through all the errors
        for (var entry : errors.entrySet()){
            List<String> errorMessages = entry.getValue();
            for (String error : errorMessages){
                //Check if any of the error messages contain the string given in the Gherkin script
                if (error.contains(expectedErrorMessage)) {
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        //Assert that a matching error message was found
        assertThat(found,is(true));
    }
}
