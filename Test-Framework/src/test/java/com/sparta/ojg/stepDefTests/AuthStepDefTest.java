package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.Account;
import com.sparta.ojg.SharedState;
import com.sparta.ojg.Utils;
import com.sparta.ojg.stepdefs.AuthStepDef;
import com.sparta.ojg.stepdefs.CommonStepDefs;
import io.cucumber.java.BeforeAll;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthStepDefTest {
    @Mock
    private SharedState sharedState;

    @Mock
    private Response response;

    @Mock
    private Account account;

    @InjectMocks
    private AuthStepDef sut;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);

        // Initialize the SharedState maps
        sharedState.headers = new HashMap<>();
        sharedState.pathParams = new HashMap<>();
    }

    @Test
    public void givenTheUsername(){
        String username = "Owen";
        sharedState.account = new Account();
        sut.theUsernameUsername(username);
        assertThat(sharedState.account.getUsername(),is(username));
    }

    @Test
    public void givenThePassword(){
        String password = "password123";
        sharedState.account = new Account();
        sut.thePasswordPassword(password);
        assertThat(sharedState.account.getPassword(),is(password));
    }

    @Test
    public void theRequestBodyContainingTheUsernameAndPasswordTest(){
        String correctJson = "{\"username\":\"Owen\",\"password\":\"password123\"}";
        sharedState.account = account;
        when(account.getCredentialsAsJson()).thenReturn(correctJson);
        sut.theRequestBodyContainingTheUsernameAndPassword();
        assertThat(sharedState.requestBody,is(correctJson));
    }

    @Test
    public void theResponseBodyShouldIncludeABearerTokenTest(){
        String responseBodyStringMock = "{\"token\":\"testToken\"}";
        ResponseBody responseBodyMock = mock(ResponseBody.class);
        sharedState.response = response;
        when(response.getBody()).thenReturn(responseBodyMock);
        when(responseBodyMock.asString()).thenReturn(responseBodyStringMock);
        sut.theResponseBodyShouldIncludeABearerToken();
        assertThat(sut.token,is("testToken"));
    }

    @Test
    public void theResponseBodyShouldNotIncludeABearerTokenTest(){
        sharedState.response = response;
        assertThat(sut.token,is(""));
    }
}
