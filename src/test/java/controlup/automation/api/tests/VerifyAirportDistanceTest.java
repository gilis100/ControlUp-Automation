package controlup.automation.api.tests;

import controlup.automation.base.ApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class VerifyAirportDistanceTest extends ApiTestBase {

	
//	Positive
	@Test
	public void verifyDistanceKixToNrt() {
	    String body = """
	        {
	          "from": "KIX",
	          "to": "NRT"
	        }
	        """;

	    Response resp =
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(body)
	        .when()
	            .post("/api/airports/distance")
	        .then()
	            .statusCode(200)
	            .extract().response();

	    Double km = resp.jsonPath().getDouble("data.attributes.kilometers");

	    assertThat(km).isNotNull();
	    assertThat(km).isGreaterThan(400.0);

	    System.out.println("Verified that the distance between KIX and NRT is greater than 400 km (" + km + " km)");
	}
	
    //Negative test: missing 'to' param should return 422
	@Test
    public void verifyDistanceMissingParamReturns422() {
        String body = """
            { "from": "KIX" }
            """;

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(body)
        .when()
            .post("/api/airports/distance")
        .then()
            .statusCode(422);

        System.out.println("Verified that distance request without 'to' param returns 422");
    }
    
    //Negative test: invalid airport code should return 422
	@Test
    public void verifyDistanceInvalidCodeReturns422() {
        String body = """
            { "from": "XXX", "to": "NRT" }
            """;

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(body)
        .when()
            .post("/api/airports/distance")
        .then()
            .statusCode(422);

        
        System.out.println("Verified that distance request with invalid airport code returns 422");
    }
}
