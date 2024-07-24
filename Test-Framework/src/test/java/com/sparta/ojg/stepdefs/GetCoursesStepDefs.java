package com.sparta.ojg.stepdefs;

import com.sparta.ojg.SharedState;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.Map;

public class GetCoursesStepDefs{
    private final SharedState sharedState;

    public GetCoursesStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }
}
