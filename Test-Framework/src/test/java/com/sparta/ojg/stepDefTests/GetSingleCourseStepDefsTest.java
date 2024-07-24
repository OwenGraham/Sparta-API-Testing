package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.stepdefs.GetSingleCourseStepDefs;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetSingleCourseStepDefsTest {
    @Mock
    private SharedState sharedState;

    @Mock
    private Response response;

    @InjectMocks
    private GetSingleCourseStepDefs sut;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        sharedState.pathParams = new HashMap<>();
    }

    @Test
    public void theResponseBodyShouldContainTheInfoForTheTECHCourseTest(){
        sharedState.response = response;

        ResponseBody responseBody = mock(ResponseBody.class);
        when(response.getBody()).thenReturn(responseBody);

        String responseBodyString = "{\"name\":\"testName\"}";
        when(responseBody.asString()).thenReturn(responseBodyString);

        sut.theResponseBodyShouldContainTheInfoForTheTECHCourse("testName");

        assertThat(sut.name,is("testName"));
    }

    @Test
    public void theIdPathParamSetAsTest(){
        sut.theIdPathParamSetAs("testId");
        assertThat(sharedState.pathParams.containsKey("id"),is(true));
        assertThat(sharedState.pathParams.get("id"),is("testId"));
    }
}
