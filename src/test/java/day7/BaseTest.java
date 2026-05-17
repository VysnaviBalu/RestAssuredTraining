package day7;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;


public class BaseTest {
	
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.useRelaxedHTTPSValidation();
	}

}
