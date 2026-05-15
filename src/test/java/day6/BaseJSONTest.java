package day6;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;


public class BaseJSONTest {
	
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.useRelaxedHTTPSValidation();
	}

}
