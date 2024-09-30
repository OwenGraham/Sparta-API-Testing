@Post
@Feature1
Feature: Obtain bearer token
  @Happy
  @StatusCode
  Scenario: Request bearer token with correct username and password and verify status code of response
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "global"
    And the request body containing the username and password
    When I send a POST request
    Then the status code of the response should be 200
  @Happy
  @Function
  Scenario: Request bearer token with correct username and password and verify response body contains token
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "global"
    And the request body containing the username and password
    When I send a POST request
    Then the response body should include a bearer token
  @Sad
  @StatusCode
  Scenario: Request bearer token with incorrect username and verify status code of response
    Given the endpoint "/Auth/login"
    And the username "string"
    And the password "global"
    When I send a POST request
    Then the status code of the response should be 401
  @Sad
  @Function
  Scenario: Request bearer token with incorrect username and verify response body doesn't contain token
    Given the endpoint "/Auth/login"
    And the username "string"
    And the password "global"
    When I send a POST request
    Then the response body should not include a bearer token
  @Sad
  @StatusCode
  Scenario: Request bearer token with incorrect username and verify status code of response
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "string"
    When I send a POST request
    Then the status code of the response should be 401
  @Sad
  @Function
  Scenario: Request bearer token with incorrect username and verify response body doesn't contain token
    Given the endpoint "/Auth/login"
    And the username "sparta"
    And the password "string"
    When I send a POST request
    Then the response body should not include a bearer token