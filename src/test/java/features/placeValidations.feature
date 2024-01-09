Feature: Validate place APIS

  @AddPlace
  Scenario Outline: Verify if place is successfully added using AddPlaceApi
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "Post" http request
    Then the api call is success with status code
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceApi"

  Examples:
    |name     |language|address          |
    |AAhouse  |English |219 Willis Street|
    |TestHouse|Telugu  |Agiripalli       |
 @DeletePlace
  Scenario: Verify if delete place functionality is working
    Given DeletePlaceApi payload
    When user calls "DeletePlaceApi" with "Post" http request
    Then the api call is success with status code
    And "status" in response body is "OK"

