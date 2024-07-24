# User Stories 

# Feature 01: Obtain Bearer Token
As an API client<br>
I want to obtain a bearer token using my username and password<br>
So that I am authorised to use other API calls<br>

## Scenario 01.01: Request bearer token with correct username and password and verify status code of response
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "sparta",<br>
"password": "global"<br>
}<br>
When I send a POST request<br>
Then the status code of the response should be 200<br>

## Scenario 01.02: Request bearer token with correct username and password and verify response body contains token
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "sparta",<br>
"password": "global"<br>
}<br>
When I send a POST request<br>
Then the response body should include a bearer token<br>

## Scenario 01.03: Request bearer token with incorrect username and verify status code of response
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "string",<br>
"password": "global"<br>
}<br>
When I send a POST request<br>
Then the status code of the response should be 401<br>
<br>
## Scenario 01.04: Request bearer token with incorrect username and verify response body doesn't contain token
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "string",<br>
"password": "global"<br>
}<br>
When I send a POST request<br>
Then the response body should not include a bearer token<br>

## Scenario 01.05: Request bearer token with incorrect password and verify status code of response
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "sparta",<br>
"password": "string"<br>
}<br>
When I send a POST request<br>
Then the status code of the response should be 401<br>

## Scenario 01.06: Request bearer token with incorrect password and verify response body doesn't contain token
Given the endpoint `/Auth/login`<br>
And the JSON body `{<br>
"username": "sparta",<br>
"password": "string"<br>
}<br>
When I send a POST request<br>
Then the response body should not include a bearer token<br>

# Feature 02: Courses endpoint
As a API client<br>
I want to request a list of all courses currently running in the Sparta academy<br>
So that I can see what courses are running, who is their trainer, and which trainee Spartans are on them<br>

## Scenario 02.01: Get all courses with auth
Given I have obtained a bearer token<br>
And the endpoint `/api/Courses`<br>
When I send a GET request<br>
Then the response status code should be 200<br>

## Scenario 02.02: Get all courses without auth
Given I have not obtained a bearer token<br>
And the endpoint `/api/Courses`<br>
When I send a GET request<br>
Then the response status code should be 401<br>

# Feature 03: Get single course
As an API client<br>
I want to request information about specific courses currently running in the Sparta academy<br>
So that I can see who is the trainer, and which trainee Spartans are on a course<br>

## Scenario 03.01: Request an existing course by ID and verify status code of response
Given I have obtained a bearer token<br>
And the endpoint `/api/Courses/{id}`<br>
And the id path param set as 1<br>
When I send a GET request<br>
Then the response status code should be 200<br>

## Scenario 03.02: Request an existing course by ID and verify request body contains requested data
Given I have obtained a bearer token<br>
And the endpoint `/api/Courses/{id}`<br>
And the id path param set as 1<br>
When I send a GET request<br>
Then the response body should contain the info for the TECH 300 course<br>

## Scenario 03.03: Request a non-existing course by ID
Given I have obtained a bearer token<br>
And the endpoint `/api/Courses/{id}`<br>
And the id path param set as 0<br>
When I send a GET request<br>
Then the response status code should be 404<br>

## Scenario 03.04: Request an existing course by ID without auth
Given I have not obtained a bearer token<br>
And the endpoint `/api/Courses/{id}`<br>
And the id path param set as 1<br>
When I send a GET request<br>
Then the response status code should be 401<br>

## Scenario 03.05: Request a course with invalid ID
Given I have obtained a bearer token<br>
And the endpoint `/api/Courses/{id}`<br>
And the id path param set as "string"<br>
When I send a GET request<br>
Then the status code of the response should be 400<br>

# Feature 04: Get all Spartans
As a API client<br>
I want to request a list of all Spartans<br>
So that I can see information about all Spartans in the academy<br>

## Scenario 04.01: Get all Spartans with auth
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a GET request<br>
Then the status code of the response should be 200<br>

## Scenario 04.02: Get all Spartans without auth
Given I have not obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a GET request<br>
Then the status code of the response should be 401<br>

# Feature 05: create Spartan
As an API client<br>
I want to be able to add new Spartans to the system<br>
So that API clients can access their data<br>

## Scenario 05.01: create Spartan with valid data and verify status code
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing **all** the **valid** data required:<br>
Then the status code of the response should be 200<br>

## Scenario 05.02: create Spartan with valid data and verify response body matches request data
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing **all** the **valid** data required:<br>
Then the data in the response body should match the data provided in the request<br>

## Scenario 05.03: create Spartan with valid data and verify it appears in subsequent GET requests
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing **all** the **valid** data required:<br>
Then the Spartan should appear in a subsequent GET request to the endpoint `/api/Spartans`<br>

## Scenario 05.04: create Spartan with invalid data and verify response status code
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing some invalid *(missing or incorrect data type)* data<br>
Then the status code of the response should be 400<br>

## Scenario 05.05: create Spartan with invalid data and verify Spartan isn't added to database
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing some invalid *(missing or incorrect data type)* data<br>
Then the Spartan should not appear in subsequent GET requests to the endpoint `/api/Spartans`<br>

## Scenario 05.06: create Spartan with invalid data and check response for descriptive error message
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans`<br>
When I send a POST request with body containing some invalid *(missing or incorrect data type)* data<br>
Then the response should include a descriptive error message<br>

# Feature 06: Get single Spartan
As a API client<br>
I want to request information about a specific Spartan<br>
So that I can quickly access their data<br>

## Scenario 06.01: Request existing Spartan by ID and verify status code of response
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the path param `id` set as 1<br>
When I make a GET request<br>
Then the status code of the response should be 200<br>

## Scenario 06.02: Request existing Spartan by ID and verify response body contains requested data
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the path param `id` set as 1<br>
When I make a GET request<br>
Then the response body should contain information about Sparty McFly<br>

## Scenario 06.03: Request a non-existing Spartan by ID
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the path param `id` set as 0<br>
Then the status code of the response should be 404<br>

## Scenario 06.04: Request Spartan with invalid ID
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the id path param set as "string"<br>
When I send a GET request<br>
Then the status code of the response should be 400<br>

# Feature 07: Delete Spartan

As an API client<br>
I want to be able to delete a Spartan's data when they leave the company<br>
So that Sparta adheres to GDPR regulations<br>

## Scenario 07.01: delete an existing Spartan by ID and verify status code
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the `id` path param set as 2<br>
When I send a DELETE request<br>
Then the status code of the response should be 204<br>

## Scenario 07.02: delete an existing Spartan by ID and verify Spartan no longer exists in database
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the `id` path param set as 2<br>
When I send a DELETE request<br>
Then the status code of the response to a subsequent GET request to the endpoint `/api/Spartans/2` should be 404<br>

## Scenario 07.03: delete a non-existing Spartan
Given I have obtained a bearer token<br>
And the endpoint `/api/Spartans/{id}`<br>
And the `id` path param set as 0<br>
When I send a DELETE request<br>
Then the status code of the response should be 404<br>

# Feature 08: Update Spartan
As an API client<br>
I want to be able to update an existing Spartan's data<br>
To keep it accurate<br>

## Scenario 08.01: update existing Spartan with valid data and verify status code of response
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 1<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `graduated` field set to `true`<br>
Then the status code of the response should be 200<br>

## Scenario 08.02: update existing Spartan with valid data and verify database is updated correctly
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 1<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `graduated` field set to `true`<br>
Then the `graduated` field of the response body from a subsequent GET request to the endpoint `/api/Spartans/1` should be `true`<br>

## Scenario 08.03: update existing Spartan with invalid *(missing/incorrect data type)* data and verify status code of response
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 1<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `graduated` field missing<br>
Then the status code of the response should be 400<br>

## Scenario 08.04: update existing Spartan with invalid *(missing/incorrect data type)* data and verify database isn't updated
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 1<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `graduated` field missing<br>
Then the `graduated` field of the response body from a subsequent GET request to the endpoint `/api/Spartans/1` should be `false`<br>

## Scenario 08.05: update non-existing Spartan
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 0<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `graduated` field set as `true`<br>
Then the status code of the response should be 404<br>

## Scenario 08.06: update existing Spartan's ID to already taken ID
Given I have obtained a bearer token<br>
And the endpoint `api/Spartans/{id}`<br>
And the `id` path param set as 1<br>
When I send a PUT request with body containing all existing data for Spartan with ID 1 apart from the `id` field set to `2`<br>
Then the status code of the response should be 400<br>