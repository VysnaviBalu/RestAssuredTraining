package day4;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;


public class BaseTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.useRelaxedHTTPSValidation();	}

}
