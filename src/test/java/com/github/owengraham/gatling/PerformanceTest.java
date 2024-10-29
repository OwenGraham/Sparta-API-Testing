package com.github.owengraham.gatling;

import com.github.owengraham.rest_assured_project.RestartDocker;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.io.IOException;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class PerformanceTest extends Simulation {
    // Runtime Parameters
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("USERS", "5")); // Store the system property USERS in a variable, and have it default to 5
    private static final int RAMP_DURATION = Integer.parseInt(System.getProperty("RAMP_DURATION","10")); // Store the system property RAMP_DURATION in a variable, and have it default to 10
    private static final int TEST_DURATION = Integer.parseInt(System.getProperty("TEST_DURATION","20")); // Store the system property TEST_DURATION in a variable, and have it default to 20

    // HTTP protocol
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Before Block
    @Override
    public void before(){
        System.out.printf("Running test with %d users%n", USER_COUNT);
        System.out.printf("Ramping users over %d seconds%n", RAMP_DURATION);
        System.out.printf("Total test duration: %d seconds", TEST_DURATION);
    }

    // HTTP calls
    // Obtain bearer token from login endpoint using valid username and password
    private static ChainBuilder authenticateValid =
            exec(http("Authenticate")
                    .post("/Auth/login")
                    .body(StringBody("{\n" +
                            " \"username\": \"sparta\",\n" +
                            " \"password\": \"global\"\n" +
                            "}"))
                    .check(status().is(200))
                    .check(jsonPath("$.token").saveAs("jwtToken")));

    // Obtain bearer token from login endpoint using valid username and password
    private static ChainBuilder authenticateInvalid =
            exec(http("Authenticate")
                    .post("/Auth/login")
                    .body(StringBody("{\n" +
                            " \"username\": \"invalid\",\n" +
                            " \"password\": \"global\"\n" +
                            "}"))
                    .check(status().is(401))
                    .check(jsonPath("$.token").notExists()));



    // Get all courses, assert 200 status code
    private static ChainBuilder getAllCourses =
            exec(http("Get all courses")
                    .get("/api/Courses")
                    .check(status().is(200)));

    // Get specific course
    private static ChainBuilder getCourse =
            exec(http("Get specific course")
                    .get("/api/Courses/1")
                    .check(status().is(200))
                    .check(jsonPath("$.name").is("TECH 300")));

    // Get all Spartans
    private static ChainBuilder getAllSpartans =
            exec(http("Get all Spartans")
                    .get("/api/Spartans")
                    .header("Authorization", "Bearer #{jwtToken}")
                    .check(status().is(200)));

    // Create Spartan
    private static ChainBuilder createSpartan =
            exec(http("Create new Spartan")
                    .post("/api/Spartans")
                    .body(RawFileBody("src/test/resources/Test Data/Valid Spartan.json"))
                    .header("Authorization", "Bearer #{jwtToken}")
                    .check(status().is(200)));

    // Get specific Spartan
    private static ChainBuilder getSpartan =
            exec(http("Get specific Spartan")
                    .get("/api/Spartans/1")
                    .header("Authorization", "Bearer #{jwtToken}")
                    .check(status().is(200)));

    // Delete Spartan
    private static ChainBuilder deleteSpartan =
            exec(http("Delete Spartan")
                    .delete("/api/Spartans/1")
                    .header("Authorization", "Bearer #{jwtToken}")
                    .check(status().is(204)));

    // Update Spartan
    private static ChainBuilder updateSpartan =
            exec(http("Update Spartan")
                    .put("/api/Spartans/2")
                    .body(RawFileBody("src/test/resources/Test Data/John Lennon updated.json"))
                    .header("Authorization", "Bearer #{jwtToken}")
                    .check(status().is(204)));


    // User journey
    private static ScenarioBuilder scn = scenario("Sparta Academy API stress test").
            exec(getAllCourses)
            .exec(getCourse)
            .exec(authenticateInvalid)
            .exec(authenticateValid)
            .exec(getAllSpartans)
            .exec(createSpartan)
            .exec(getSpartan)
            .exec(deleteSpartan)
            .exec(updateSpartan);

    // Load simulation
    {
        setUp(
                scn.injectOpen(
                        nothingFor(5),
                        rampUsers(USER_COUNT).during(RAMP_DURATION)
                ).protocols(httpProtocol)
        ).maxDuration(TEST_DURATION);
    }

    // After block - restart Docker image
    @Override
    public void after(){
        try {
            RestartDocker.restart();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
