Feature: Get single course
  Scenario: Request an existing course by ID
    Given I have obtained a bearer token
    And the endpoint "/api/Courses/{id}"
    And the id path param set as 1
    When I send a GET request
    Then the status code of the response should be 200
    And the response body should contain the info for the "TECH 300" course

  Scenario: Request a non-existing course by ID
    Given I have obtained a bearer token
    And the endpoint "/api/Courses/{id}"
    And the id path param set as 0
    When I send a GET request
    Then the status code of the response should be 404

  Scenario: Request a course by ID without auth
    Given I have not obtained a bearer token
    And the endpoint "/api/Courses/{id}"
    And the id path param set as 1
    When I send a GET request
    Then the status code of the response should be 401

  Scenario: Request a course with invalid ID
    Given I have obtained a bearer token
    And the endpoint "/api/Courses/{id}"
    And the id path param set as "string"
    When I send a GET request
    Then the status code of the response should be 400