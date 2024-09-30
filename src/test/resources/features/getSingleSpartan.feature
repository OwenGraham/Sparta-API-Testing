@Feature6
@Get
Feature: Get Spartan by ID endpoint
  @Happy
  @StatusCode
  Scenario: Request existing Spartan by ID and verify status code of response
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 1
    When I send a GET request
    Then the status code of the response should be 200

  @Happy
  @Function
  Scenario: Request existing Spartan by ID and verify response body contains requested data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 1
    When I send a GET request
    Then the response body should contain information about "Sparty McFly"

  @Sad
  @StatusCode
  Scenario: Request a non-existing Spartan by ID
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 0
    When I send a GET request
    Then the status code of the response should be 404

  @Sad
  @StatusCode
  Scenario: Request Spartan with invalid ID
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as "string"
    When I send a GET request
    Then the status code of the response should be 400

