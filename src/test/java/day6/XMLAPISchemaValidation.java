package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class XMLAPISchemaValidation extends BaseXMLTest{
	
	@Test
	void validateXMLAPISchemeUsingClassPath() {
		
		given()
		
		.when()
		 .get("/note.xml")
		.then()
		 .statusCode(200)
		 .body(matchesXsdInClasspath("schemas/noteSchema.xsd"));
		
	}

}
