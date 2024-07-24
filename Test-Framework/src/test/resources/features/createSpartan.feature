@Feature5
@Post
Feature: Create Spartan
  @Happy
  @StatusCode
  Scenario: create Spartan with valid data and verify status code
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "src/test/resources/Test Data/Valid Spartan.json"
    When I send a POST request with body from file
    Then the status code of the response should be 200

  @Happy
  Scenario: create Spartan with valid data and verify response body matches request data
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "src/test/resources/Test Data/Valid Spartan.json"
    When I send a POST request with body from file
    Then the data in the response body should match the data provided in the request from the file "src/test/resources/Test Data/Valid Spartan.json"

  @Happy
  @Function
  Scenario: create Spartan with valid data and verify it appears in subsequent GET requests
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "src/test/resources/Test Data/Valid Spartan.json"
    When I send a POST request with body from file
    Then the Spartan should appear in subsequent GET requests to the get all Spartans endpoint

  @Sad
  @StatusCode
  Scenario Outline: create Spartan with invalid data and verify response status code
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "<file>"
    When I send a POST request with body from file
    Then the status code of the response should be 400

    Examples:
      | file |
      #This JSON data is missing the lastName field
      | src/test/resources/Test Data/Invalid Spartan 1.json |
      #This JSON data has a String as id
      | src/test/resources/Test Data/Invalid Spartan 2.json |
      #This JSON data has a int as firstName
      | src/test/resources/Test Data/Invalid Spartan 3.json |

  @Sad
  @Function
  Scenario Outline: create Spartan with invalid data and verify Spartan isn't added to database
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "<file>"
    When I send a POST request with body from file
    Then the Spartan should not appear in subsequent GET requests to the get all Spartans endpoint

    Examples:
      | file |
      #This JSON data is missing the lastName field
      | src/test/resources/Test Data/Invalid Spartan 1.json |
      #This JSON data has a String as id
      | src/test/resources/Test Data/Invalid Spartan 2.json |
      #This JSON data has a int as firstName
      | src/test/resources/Test Data/Invalid Spartan 3.json |

  @Sad
  @Message
  Scenario Outline: create Spartan with invalid data and check response for descriptive error message
    Given I have obtained a bearer token
    And the endpoint "/api/Spartans"
    And the request body containing data taken from the file "<file>"
    When I send a POST request with body from file
    Then the response should include a "<descriptive error message>"

    Examples:
      | file                                              | descriptive error message |
      #This JSON data is missing the lastName field
      | src/test/resources/Test Data/Invalid Spartan 1.json | The LastName field is required.|
      #This JSON data has a String as id
      | src/test/resources/Test Data/Invalid Spartan 2.json | Could not convert string to integer   |
      #This JSON data has a int as firstName
      | src/test/resources/Test Data/Invalid Spartan 3.json | The field FirstName must be a string with a minimum length of 6 and a maximum length of 50.   |
