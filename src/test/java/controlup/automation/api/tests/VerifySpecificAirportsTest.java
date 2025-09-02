package controlup.automation.api.tests;

import controlup.automation.base.ApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class VerifySpecificAirportsTest extends ApiTestBase {

	//Positive
    @Test
    public void verifySpecificAirportsExist() {
        Response resp =
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/api/airports")
            .then()
                .statusCode(200)
                .extract().response();

        List<String> airportNames = resp.jsonPath().getList("data.attributes.name");

        assertThat(airportNames)
            .contains("Akureyri Airport", "St. Anthony Airport", "CFB Bagotville");

        System.out.println("Verified that the response includes Akureyri Airport, St. Anthony Airport, and CFB Bagotville");
    }
    
 // Negative – airport not present
    @Test
    public void verifyNonExistingAirportNotInList() {
        Response resp =
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/api/airports")
            .then()
                .statusCode(200)
                .extract().response();

        List<String> names = resp.jsonPath().getList("data.attributes.name");

        assertThat(names)
            .doesNotContain("TLV Airport");

        System.out.println("Verified that 'TLV Airport' is not present in the list");
    }
}
