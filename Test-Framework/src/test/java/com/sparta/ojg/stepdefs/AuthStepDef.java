package com.sparta.ojg.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AuthStepDef extends StepDefSuper{
    private String endpoint;
    private String username;
    private String password;

    @Given("the endpoint {string}")
    public void theEndpointEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @And("the username {string}")
    public void theUsernameUsername(String username) {
        this.username = username;
    }


    @And("the password {string}")
    public void thePasswordPassword(String password) {
        this.password = password;
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() {
        System.out.println(endpoint + username + password);
    }
}
