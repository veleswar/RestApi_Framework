package StepDefinitions;

import Resources.ApiEndPoints;
import Resources.TestDataBuild;
import Resources.Utils;
import groovy.transform.SourceURI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.keyStore;
import static org.junit.Assert.assertEquals;

public class PlaceAPiStepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification rb;
    Response opResponse;
    Response buildResp;


    static String placeId;
    TestDataBuild testData = new TestDataBuild();

    @Given("Add Place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {


        res = given().spec(requestSpecification()).log().all()
                .body(testData.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String endPoint, String httpMethod) {
        ApiEndPoints apiEndPoint = ApiEndPoints.valueOf(endPoint);
        rb = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        switch (httpMethod.toUpperCase()) {
            case "GET":
                buildResp = res.when().get(apiEndPoint.getResource());
                break;
            case "POST":
                buildResp = res.when().post(apiEndPoint.getResource());
                break;
            case "DELETE":
                buildResp = res.when().delete(apiEndPoint.getResource());
                break;
            default:
                System.out.println("Unsupported Http method: " + httpMethod);
        }


    }

    @Then("the api call is success with status code")
    public void the_api_call_is_success_with_status_code() {

        opResponse = buildResp.then().spec(rb).extract().response();
        assertEquals(opResponse.getStatusCode(), 200);


    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue) {


        assertEquals(getJsonPath(opResponse, keyValue), Expectedvalue);

    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String endPoint) throws IOException {

         placeId = getJsonPath(opResponse, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(endPoint, "GET");
        String name = getJsonPath(buildResp, "name");
        assertEquals(name, expectedName);

    }

    @Given("DeletePlaceApi payload")

    public void delete_place_api_payload() throws IOException {

        res = given().spec(requestSpecification()).log().all().body(testData.deletePlacePayLoad(placeId));

    }

}