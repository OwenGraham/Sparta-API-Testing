package com.github.owengraham.rest_assured_project.stepDefTests;

import com.github.owengraham.rest_assured_project.SharedState;
import com.github.owengraham.rest_assured_project.Utils;
import com.github.owengraham.rest_assured_project.stepdefs.CommonStepDefs;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonStepDefsTest {

    @Mock
    private SharedState sharedState;

    @Mock
    private Utils utils;

    @Mock
    private Response response;

    @InjectMocks
    private CommonStepDefs commonStepDefs;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the SharedState maps
        sharedState.headers = new HashMap<>();
        sharedState.pathParams = new HashMap<>();

        // Set the Utils mock
        commonStepDefs.utils = utils;
    }

    @Test
    public void testTheEndpointEndpoint() {
        String endpoint = "/test/endpoint";
        commonStepDefs.theEndpointEndpoint(endpoint);
        assertThat(sharedState.endpoint, is(endpoint));
    }

    @Test
    public void testIHaveObtainedABearerToken() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = "{\"token\":\"testToken\"}";

        when(utils.postRequest()).thenReturn(response);
        ResponseBody responseBody = mock(ResponseBody.class);
        when(response.getBody()).thenReturn(responseBody);
        when(responseBody.asString()).thenReturn(jsonResponse);

        commonStepDefs.IHaveObtainedABearerToken();

        verify(utils).postRequest();
        assertThat(sharedState.endpoint, is("/Auth/login"));
        assertThat(sharedState.headers.get("Authorization"), is("Bearer testToken"));
    }

    @Test
    public void testIHaveNotObtainedABearerToken() {
        sharedState.headers.put("Authorization", "Bearer testToken");
        commonStepDefs.iHaveNotObtainedABearerToken();
        assertThat(sharedState.headers.containsKey("Authorization"), is(false));
    }

    @Test
    public void testTheIdPathParamSetAs() {
        int id = 123;
        commonStepDefs.theIdPathParamSetAs(id);
        assertThat(sharedState.pathParams.get("id"), is(String.valueOf(id)));
    }

    @Test
    public void testISendAGETRequest() {
        when(utils.getRequest()).thenReturn(response);
        commonStepDefs.iSendAGETRequest();
        verify(utils).getRequest();
        assertThat(sharedState.response, is(response));
    }

    @Test
    public void testISendAPOSTRequest() {
        when(utils.postRequest()).thenReturn(response);
        commonStepDefs.iSendAPOSTRequest();
        verify(utils).postRequest();
        assertThat(sharedState.response, is(response));
    }

    @Test
    public void testISendAPOSTRequestWithBodyFromFile() {
        when(utils.postRequestWithBodyFile()).thenReturn(response);
        commonStepDefs.iSendAPOSTRequestWithBodyFromFile();
        verify(utils).postRequestWithBodyFile();
        assertThat(sharedState.response, is(response));
    }

    @Test
    public void testISendAPUTRequest() {
        when(utils.putRequest()).thenReturn(response);
        commonStepDefs.iSendAPUTRequest();
        verify(utils).putRequest();
        assertThat(sharedState.response, is(response));
    }

    @Test
    public void testISendADELETERequest() {
        when(utils.deleteRequest()).thenReturn(response);
        commonStepDefs.iSendADELETERequest();
        verify(utils).deleteRequest();
        assertThat(sharedState.response, is(response));
    }

    @Test
    public void testTheStatusCodeOfTheResponseShouldBe() {
        int statusCode = 200;
        when(response.statusCode()).thenReturn(statusCode);
        sharedState.response = response;
        commonStepDefs.theStatusCodeOfTheResponseShouldBe(statusCode);
        verify(response).statusCode();
        assertThat(sharedState.response.statusCode(), is(statusCode));
    }
}
