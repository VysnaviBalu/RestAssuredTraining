package day3;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.*;
import org.hamcrest.Matchers;



public class LoggingDemo extends BaseTest {
	
	
	@Test(priority=1)
	public void testLogsStatusCode() {
		given()
		
		.when()
		 .get("/comments/1")
		.then()
		 .log().status();
		
		System.out.println("******************");
	}
	
	@Test(priority=2)
	public void testLogsAll() {
		
		given()
		
		.when()
		 .get("/comments/1")
		.then()
		 .statusCode(200)
		 .log().all();
		
		System.out.println("******************");
		 
	}
	
	
	@Test(priority=3)
	public void testLogsHeader() {
		given()
		
		.when()
		 .get("/comments/1")
		.then()
		 .statusCode(200)
		 .log().headers();
		
		System.out.println("******************");
	}
	
	@Test(priority=4)
	public void testLogsCookies() {
		given()
		
		.when()
		 .get("/comments/1")
		.then()
		 .statusCode(200)
		 .log().cookies();
		
		System.out.println("******************");
	}
	
	
	@Test(priority=5)
	public void testLogsBody() {
		given()
		
		.when()
		 .get("/comments/1")
		.then()
		 .statusCode(200)
		 .log().body();
		
		System.out.println("******************");
		
	}
	

}
