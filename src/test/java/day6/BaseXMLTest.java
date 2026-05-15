package day6;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseXMLTest {
	
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://www.w3schools.com/xml";
		RestAssured.useRelaxedHTTPSValidation();		
	}

}
