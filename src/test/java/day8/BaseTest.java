package day8;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;


public class BaseTest {
	
	String token = System.getenv("GOREST_TOKEN");
    Faker faker = new Faker();
	
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		RestAssured.useRelaxedHTTPSValidation();
	}
}
