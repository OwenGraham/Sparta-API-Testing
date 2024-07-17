package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @And("the Spartan should not appear in subsequent GET requests to the get all Spartans endpoint")
    public void theSpartanShouldNotAppearInSubsequentGETRequestsToTheGetAllSpartansEndpoint() {
        sharedState.endpoint = "/api/Spartans";
        Utils utils = new Utils(sharedState);
        Response response = utils.getRequest();
        String spartansString = response.getBody().toString();

        // Extract data from the JSON array in the response
        List<Map<String, ?>> users = response.jsonPath().getList("$");

        boolean found = false;
        for (Map<String, ?> user : users) {
            if (user.get("id").equals(0)) found = true;
        }
        assertThat(found,is(false));

//        JSONArray spartansArray = new JSONArray(response.getBody().toString());
//
//        boolean found = false;
//        for (var spartan : spartansArray){
//            JSONObject spartanJson = (JSONObject) spartan;
//            if (spartanJson.get("id").equals(0)) found = true;
//        }
//        assertThat(found,is(false));
    }

    @And("the Spartan should appear in subsequent GET requests to the get all Spartans endpoint")
    public void theSpartanShouldAppearInSubsequentGETRequestsToTheGetAllSpartansEndpoint() {
        sharedState.endpoint = "/api/Spartans";
        Utils utils = new Utils(sharedState);
        Response response = utils.getRequest();
        JSONArray spartansArray = new JSONArray(response.getBody());

        boolean found = false;
        for (var spartan : spartansArray){
            JSONObject spartanJson = (JSONObject) spartan;
            if (spartanJson.get("id").equals(0)) found = true;
        }
        assertThat(found,is(true));
    }

    @And("the response should include a {string}")
    public void theResponseShouldIncludeA(String expectedErrorMessage) {
        Map<String,List<String>> errors = sharedState.response.jsonPath().getMap("errors");
        boolean found = false;
        for (var entry : errors.entrySet()){
            List<String> errorMessages = entry.getValue();
            for (String error : errorMessages){
                if (error.contains(expectedErrorMessage)) {
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        assertThat(found,is(true));
    }
}
