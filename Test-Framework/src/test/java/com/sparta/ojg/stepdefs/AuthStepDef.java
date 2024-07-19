package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthStepDef{
    private final SharedState sharedState;

    public AuthStepDef(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @And("the username {string}")
    public void theUsernameUsername(String username) {
        //Set the username field of the shared state's account object to the username given in the Gherkin script
        sharedState.account.setUsername(username);
    }

    @And("the password {string}")
    public void thePasswordPassword(String password) {
        //Set the password field of the shared state's account object to the password given in the Gherkin script
        sharedState.account.setPassword(password);
    }

    @And("the request body containing the username and password")
    public void theRequestBodyContainingTheUsernameAndPassword() {
        //Set the request body as the shared state's account object in JSON form
        sharedState.requestBody = sharedState.account.getCredentialsAsJson();
    }

    @And("the response body should include a bearer token")
    public void theResponseBodyShouldIncludeABearerToken() {
        // Assert that the JSON body of the response contains a value for the token field
        String responseBody = sharedState.response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String token = jsonPath.get("token");
        assertThat(token, notNullValue());
    }

    @And("the response body should not include a bearer token")
    public void theResponseBodyShouldNotIncludeABearerToken() {
        //Assert that the JSON body of the response does not contain a value for the token field
        String responseBody = sharedState.response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String token = jsonPath.get("token");
        assertThat(token,not(notNullValue()));
    }
}
