Feature: Delete Spartan by ID
  Scenario: delete an existing Spartan by ID and verify status code
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 6
    When I send a DELETE request
    Then the status code of the response should be 204

  Scenario Outline: delete an existing Spartan by ID and verify Spartan no longer exists in database
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 6
    When I send a DELETE request
    Then the status code of the response body from a subsequent GET request to the get Spartan with <id> endpoint should be <expected>
    Examples:
      | id | expected |
      | 6  | 404      |

  Scenario: delete a non-existing Spartan
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans/{id}"
    And the id path param set as 0
    When I send a DELETE request
    Then the status code of the response should be 404