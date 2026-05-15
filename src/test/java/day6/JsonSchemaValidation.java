package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import io.restassured.path.json.JsonPath;
import static io.restassured.module.jsv.JsonSchemaValidator.*;



public class JsonSchemaValidation extends BaseJSONTest{

	@Test
	void validateJsonSchemaUsingClassPath() {
	
		
		               given()
				
				       .when()
				        .get("/store")
				       .then()
				        .statusCode(200)			
				        .body(matchesJsonSchemaInClasspath("schemas/storeSchema.json"));
				        
	}
}
