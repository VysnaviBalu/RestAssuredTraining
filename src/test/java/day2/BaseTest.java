package day2;

import  io.restassured.RestAssured;
import org.testng.annotations.*;

public class BaseTest {
	
	@BeforeClass
	public void setup() {
		// Run the Json server to start the Local API
		RestAssured.baseURI = "http://localhost:3000";
	}

}
