package day7;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class ArraysInRestAssured extends BaseTest {
	
	@Test
	void testQueryParam() {
		
		System.out.println("*********** Query Param ************");
		
		given()
		 .queryParam("userId", "1")
		.when()
		 .get("/posts")
		.then()
		 .statusCode(200)
		 .body("userId",everyItem(equalTo(1))) 
		 .log().all();
		
	}
	
	//@Test
	void testArrayAsQueryParamCommaSeparated() {
		
		System.out.println("*********** Comma Separated Query Param ************");
		
		given()
		 .queryParam("userId", "1,2")
		.when()
		 .get("/posts")
		.then()
		 .statusCode(200)
		 .log().all();
		
	}
	
	@Test
	void testArrayAsMultipleQueryParameter() {
		
		System.out.println("************ Multiple Query Paramater ***********");
		
		given()
		 .queryParam("userId", 1)
		 .queryParam("userId", 2)
		.when()
		 .get("/posts")
		.then()
		 .statusCode(200)
		 .log().all();
		 
	}
	
	@Test
	void testArrayQueryParamAsList() {
		
		System.out.println("************* Query Param As List **************** ");
		
		List<Integer> userIDs = Arrays.asList(1,2,3);
		
		given()
		 .queryParam("userId",userIDs)
		.when()
		 .get("/posts")
		.then()
		 .statusCode(200)
		 .log().all();
		
	}
	

}
