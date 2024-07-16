Feature: Create Spartan
  Scenario: create Spartan with valid data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing valid data taken from the file "src/test/resources/Test Data/Valid Spartan.json"
    When I send a POST request with body from file
    Then the status code of the response should be 200
    And the data in the response body should match the data provided in the request from the file "src/test/resources/Test Data/Valid Spartan.json"

  Scenario: create Spartan with invalid data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing invalid data taken from the file "src/test/resources/Test Data/Invalid Spartan.json"
    When I send a POST request with body from file
    Then the status code of the response should be 400
#    And the Spartan should not appear in subsequent GET requests to the endpoint /api/Spartans