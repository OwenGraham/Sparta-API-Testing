Feature: Courses endpoint
  Scenario: Get all courses
    Given the endpoint "/api/Courses"
    When I send a GET request
    Then the status code of the response should be "200"