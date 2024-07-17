Feature: Create Spartan
  Scenario: create Spartan with valid data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing valid data taken from the file "src/test/resources/Test Data/Valid Spartan.json"
    When I send a POST request with body from file
    Then the status code of the response should be 200
    And the data in the response body should match the data provided in the request from the file "src/test/resources/Test Data/Valid Spartan.json"
    And the Spartan should appear in subsequent GET requests to the get all Spartans endpoint

  Scenario Outline: create Spartan with invalid data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing invalid data taken from the file "<file>"
    When I send a POST request with body from file
    Then the status code of the response should be 400
    And the Spartan should not appear in subsequent GET requests to the get all Spartans endpoint
    And the response should include a "<descriptive error message>"


    Examples:
      | file                                              | descriptive error message |
      #This JSON data is missing the lastName field
      | src/test/resources/Test Data/Invalid Spartan 1.json | The LastName field is required.|
      #This JSON data has a String as id
      | src/test/resources/Test Data/Invalid Spartan 2.json | Could not convert string to integer   |
      #This JSON data has a int as firstName
      | src/test/resources/Test Data/Invalid Spartan 3.json | The field FirstName must be a string with a minimum length of 6 and a maximum length of 50.   |
