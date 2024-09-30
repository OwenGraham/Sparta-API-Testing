package com.github.owengraham.rest_assured_project.stepDefTests;

import com.github.owengraham.rest_assured_project.SharedState;
import com.github.owengraham.rest_assured_project.stepdefs.CreateSpartanStepDefs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
