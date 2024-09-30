@Feature8
@Put
Feature: update Spartan data by ID
  @Happy
  @StatusCode
  Scenario Outline: update existing Spartan with valid data and verify status code of response
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as 2
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
    Then the status code of the response should be 200
    Examples:
      | file |
      | src/test/resources/Test Data/John Lennon updated.json |

  @Happy
  @Function
  Scenario Outline: update existing Spartan with valid data and verify database is updated correctly
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as <id>
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
    Then the "<field>" of the response body from a subsequent GET request to the get Spartan with <id> endpoint should be "<expected>"
    Examples:
      | id | file | field | expected |
      | 2  | src/test/resources/Test Data/John Lennon updated.json | graduated | true |

  @Sad
  @StatusCode
  Scenario Outline: update existing Spartan with invalid (missing/incorrect data type) data and verify status code of response
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as <id>
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
    Then the status code of the response should be 400
    Examples:
      | id | file |
      # The graduated field is missing from this data
      | 3  | src/test/resources/Test Data/Invalid updated McCartney.json |
      # The firstName field in this data is a number
      | 4  | src/test/resources/Test Data/Invalid updated Ringo.json     |

  @Sad
  @Function
  Scenario Outline: update existing Spartan with invalid (missing/incorrect data type) data and verify database isn't updated
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as <id>
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
    Then the "<field>" of the response body from a subsequent GET request to the get Spartan with <id> endpoint should be "<expected>"
    Examples:
      | id | file | field | expected |
      # The graduated field is missing from this data
      | 3  | src/test/resources/Test Data/Invalid updated McCartney.json | firstName | John |
      # The firstName field in this data is a number
      | 4  | src/test/resources/Test Data/Invalid updated Ringo.json     | firstName | Ringo |

  @Sad
  @StatusCode
  Scenario Outline: update non-existing Spartan
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as 0
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
    Then the status code of the response should be 404
    Examples:
      | file |
      | src/test/resources/Test Data/John Lennon updated.json |

  @Sad
  @StatusCode
  Scenario Outline: update existing Spartan's ID to already taken ID
    Given I have obtained a bearer token
    And the endpoint "api/Spartans/{id}"
    And the id path param set as 5
    And the request body containing data taken from the file "<file>"
    When I send a PUT request
  #with body containing all existing data for Spartan with ID 1 apart from the id field set to 2
    Then the status code of the response should be 400
    Examples:
      | file |
      # Attempting to change Spartan with ID 5's ID field to 2
      | src/test/resources/Test Data/John Lennon updated.json |