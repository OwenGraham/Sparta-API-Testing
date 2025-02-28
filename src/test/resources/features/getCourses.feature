@Feature2
@Get
Feature: Courses endpoint
  @Happy
  Scenario: Get all courses with auth
    Given I have obtained a bearer token
    And the endpoint "/api/Courses"
    When I send a GET request
    Then the status code of the response should be 200

  @Sad
  Scenario: Get all courses without auth
    Given I have not obtained a bearer token
    And the endpoint "/api/Courses"
    When I send a GET request
    Then the status code of the response should be 401