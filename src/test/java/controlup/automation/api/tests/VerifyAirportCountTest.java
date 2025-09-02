package controlup.automation.api.tests;

import controlup.automation.base.ApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class VerifyAirportCountTest extends ApiTestBase {

	 // Positive
	 @Test
	    public void verifyAirportsCount() {
	        Response resp =
	            given()
	                .accept(ContentType.JSON)
	                .when()
	                .get("/api/airports")
	                .then()
	                .statusCode(200)
	                .extract().response();


	        int count = resp.jsonPath().getList("data").size();
	        assertThat(count).isEqualTo(30);
	        
	        System.out.println("Verified that the response contains exactly " + count + " airports");

	    }
	 
	   // Negative – invalid endpoint
	    @Test
	    public void verifyAirportsInvalidEndpointReturns404() {
	        given()
	            .accept(ContentType.JSON)
	        .when()
	            .get("/api/airportzzz")
	        .then()
	            .statusCode(404);

	        System.out.println("Verified 404 for invalid airports endpoint");
	    }
	
	}
