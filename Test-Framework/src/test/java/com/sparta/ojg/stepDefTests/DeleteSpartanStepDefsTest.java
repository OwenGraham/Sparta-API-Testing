package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import com.sparta.ojg.stepdefs.CreateSpartanStepDefs;
import com.sparta.ojg.stepdefs.DeleteSpartanStepDefs;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteSpartanStepDefsTest {
    @Mock
    private SharedState sharedState;

    @Mock
    private Response response;

    @Mock
    private Utils utils;

    @InjectMocks
    private DeleteSpartanStepDefs sut;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        sut.utils = utils;
        sut.response = response;
        sharedState.pathParams = new HashMap<>();
    }

    @Test
    public void theStatusCodeOfTheResponseBodyFromASubsequentGETRequestToTheGetSpartanWithIdEndpointShouldBeTest(){
        when(utils.deleteRequest()).thenReturn(response);
        when(response.statusCode()).thenReturn(200);
        sut.theStatusCodeOfTheResponseBodyFromASubsequentGETRequestToTheGetSpartanWithIdEndpointShouldBe(0,200);
        verify(utils).deleteRequest();
        assertThat(sharedState.endpoint,is("/api/Spartans/{id}"));
        assertThat(sharedState.pathParams.containsKey("id"),is(true));
    }
}
