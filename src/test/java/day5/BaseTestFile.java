package day5;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class BaseTestFile {
	
	@BeforeClass
	void setup() {
		
		RestAssured.baseURI = "http://localhost:3001";;
		RestAssured.useRelaxedHTTPSValidation();
	}

}
