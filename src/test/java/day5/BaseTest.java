package day5;

import io.restassured.*;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://www.w3schools.com/";
		RestAssured.useRelaxedHTTPSValidation();
		
	}
}
