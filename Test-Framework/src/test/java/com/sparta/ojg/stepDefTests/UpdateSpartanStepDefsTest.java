package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import com.sparta.ojg.stepdefs.UpdateSpartanStepDefs;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class UpdateSpartanStepDefsTest {
    @Mock
    private SharedState sharedState;

    @Mock
    private Response response;

    @Mock
    private Utils utils;

    @InjectMocks
    private UpdateSpartanStepDefs sut;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        sharedState.pathParams = new HashMap<>();
        sut.utils = utils;
    }

    @Test
    public void theFieldOfTheResponseBodyFromASubsequentGETRequestToTheGetAllSpartanWithIdEndpointShouldBeTrueTest(){
        sharedState.response = response;
        String testString = "{\"test\":\"string\"}";
        ResponseBody responseBody = mock(ResponseBody.class);
        when(response.getBody()).thenReturn(responseBody);
        when(responseBody.asString()).thenReturn(testString);
        when(utils.getRequest()).thenReturn(response);

        sut.theFieldOfTheResponseBodyFromASubsequentGETRequestToTheGetAllSpartanWithIdEndpointShouldBeTrue("test",0,"string");

        assertThat(sharedState.endpoint,is("/api/Spartans/{id}"));
        assertThat(sharedState.pathParams.containsKey("id"),is(true));
        verify(utils).getRequest();
        assertThat(sut.actual,is("string"));
    }
}
