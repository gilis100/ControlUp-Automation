package controlup.automation.base;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.annotations.BeforeClass;

public abstract class ApiTestBase {

	@BeforeClass(alwaysRun = true)
	public void setupApiBase() {
		RestAssured.baseURI = "https://airportgap.com";
		RestAssured.filters((Filter) (req, res, ctx) -> logConfig(req, ctx, res));
	}

	private Response logConfig(FilterableRequestSpecification req,
			FilterContext ctx,
			FilterableResponseSpecification res) {
		System.out.println(">>> " + req.getMethod() + " " + req.getURI());
		Response response = ctx.next(req, res);
		System.out.println("<<< Status Code: " + response.getStatusCode());
		return response;
	}
}
