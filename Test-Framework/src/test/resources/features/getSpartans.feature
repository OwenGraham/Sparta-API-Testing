Feature: Get Spartans endpoint
  Scenario: Get all Spartans with auth
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    When I send a GET request
    Then the status code of the response should be 200

    Scenario: Get all Spartans without auth
    Given I have not obtained a bearer token
    And the endpoint "/api/Spartans"
    When I send a GET request
    Then the status code of the response should be 401