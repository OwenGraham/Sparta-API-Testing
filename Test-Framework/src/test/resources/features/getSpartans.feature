Feature: Get Spartans endpoint
  Scenario: Get all Spartans
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    When I send a GET request
    Then the status code of the response should be 200