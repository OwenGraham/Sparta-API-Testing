package com.sparta.ojg.stepDefTests;

import com.sparta.ojg.Account;
import com.sparta.ojg.SharedState;
import com.sparta.ojg.stepdefs.AuthStepDef;
import com.sparta.ojg.stepdefs.CreateSpartanStepDefs;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateSpartanStepDefsTest {
    @Mock
    private SharedState sharedState;

    @InjectMocks
    private CreateSpartanStepDefs sut;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void theRequestBodyContainingDataTakenFromTheFileTest(){
        sut.theRequestBodyContainingDataTakenFromTheFile("src/test/resources/Test Data/Valid Spartan.json");
        assertThat(sharedState.bodyFile.getPath(),is("src\\test\\resources\\Test Data\\Valid Spartan.json"));
    }
}
