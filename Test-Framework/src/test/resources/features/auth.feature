Feature: Obtain bearer token
  Scenario: Request bearer token with correct username and password
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "global"
    When I send a POST request
    Then the status code of the response should be "200"
    And the response body should include a bearer token

  Scenario: Request bearer token with incorrect username
    Given the endpoint "/Auth/login"
    And the username "string"
    And the password "global"
    When I send a POST request
    Then the status code of the response should be "401"
    And the response body should not include a bearer token

  Scenario: Request bearer token with incorrect username
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "string"
    When I send a POST request
    Then the status code of the response should be "401"
    And the response body should not include a bearer token