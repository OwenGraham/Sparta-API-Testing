Feature: Obtain bearer token
  Scenario: Request bearer token with correct username and password
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "global"
    When I send a POST request