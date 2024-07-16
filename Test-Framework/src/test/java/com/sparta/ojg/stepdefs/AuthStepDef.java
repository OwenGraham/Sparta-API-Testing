package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class AuthStepDef extends StepDefSuper{
    private final SharedState sharedState;

    public AuthStepDef(SharedState sharedState) {
        this.sharedState = sharedState;
    }


    @Given("the endpoint {string}")
    public void theEndpointEndpoint(String endpoint) {
        sharedState.endpoint = endpoint;
    }

    @And("the username {string}")
    public void theUsernameUsername(String username) {
        sharedState.account.setUsername(username);
    }


    @And("the password {string}")
    public void thePasswordPassword(String password) {
        sharedState.account.setPassword(password);
    }

    @And("the response body should include a bearer token")
    public void theResponseBodyShouldIncludeABearerToken() {
        MatcherAssert.assertThat(sharedState.response.getBody().jsonPath().get("token"),Matchers.notNullValue());
    }

    @And("the response body should not include a bearer token")
    public void theResponseBodyShouldNotIncludeABearerToken() {
        MatcherAssert.assertThat(sharedState.response.getBody().jsonPath().get("token"),Matchers.not(Matchers.notNullValue()));
    }

    @And("the request body containing the username and password")
    public void theRequestBodyContainingTheUsernameAndPassword() {
        sharedState.requestBody = sharedState.account.getCredentialsAsJson();
    }
}
