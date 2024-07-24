package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.stepdefs.GetSingleCourseStepDefs;
import com.sparta.ojg.stepdefs.GetSingleSpartanStepDefs;
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

public class GetSingleSpartanStepDefsTest {
    @Mock
    private SharedState sharedState;

    @Mock
    private Response response;

    @InjectMocks
    private GetSingleSpartanStepDefs sut;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        sharedState.pathParams = new HashMap<>();
    }

    @Test
    public void theResponseBodyShouldContainInformationAboutTest(){
        sharedState.response = response;
        String testNameJson = "{\"firstName\":\"firstNameTest\",\"lastName\":\"lastNameTest\"}";
        ResponseBody responseBody = mock(ResponseBody.class);
        when(response.getBody()).thenReturn(responseBody);
        when(responseBody.asString()).thenReturn(testNameJson);
        sut.theResponseBodyShouldContainInformationAbout("firstNameTest lastNameTest");
        assertThat(sut.firstName,is("firstNameTest"));
        assertThat(sut.lastName,is("lastNameTest"));
    }
}
