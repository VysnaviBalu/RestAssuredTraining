
package day1;
//Core Rest Assured static imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


//Hamcrest matchers (for assertions)
import static org.hamcrest.Matchers.*;

//TestNG
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HTTPResponse extends BaseTest {
	
	// ─── GET ────────────────────────────────────────
	@Test (priority = 1)
	void getUser() {
		
		when()
		 .get("/users")
		 
		.then()
		 .statusCode(200)
		 .body("size()", equalTo(10));
		 
	}
	
	
	// ─── POST ───────────────────────────────────────
	@Test (priority = 2)
	void createUser() {
		
		given()
		 .contentType(ContentType.JSON)
		 .body(buildPostBody("Awesomeness","Learning RestAssured",1 ))
		 
		.when() 
		  .post("/posts")
		
		.then()  
		 .statusCode(201)
		 .log().all();
		 	 
				
	}
	
	
	// ─── PUT ────────────────────────────────────────
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void putUser() {	
		
		given()
		 .contentType(ContentType.JSON)
		 .body(buildPostBody("awesomeME","Learning To Code",1 ))
		
		.when()
		 .put("/posts/1")
		 
		.then()
		 .statusCode(200)
		 .log().all();
		
	}
	
	// ─── DELETE ─────────────────────────────────────
	@Test(priority = 4, dependsOnMethods = {"createUser","putUser"})
	void deleteUser() {
		given()
		
		.when()
		 .delete("/posts/1")
		 
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
   
	@Test
	void getAllUserDetails() {
		Response response =
				given()
				
				.when()
				 .get("/users")
				 
				.then() 
				 .statusCode(200)
				 .extract()
				 .response();
		
		// Extract full response as String
		String responseBody          = response.asString();
		// Extract specific field - first user name
		String firstName             = response.jsonPath().getString("[0].name");
		// Extract specific field - Second user email address
		String secondEmail           = response.jsonPath().getString("[1].email");
		// Extract all the names
		List<String> allUserNames    = response.jsonPath().getList("name");
		// Extract all the email address
		List<String> allEmailAddress = response.jsonPath().getList("email");
		
		System.out.println("Full Response:" +responseBody);
		System.out.println("FirstName: "+firstName);
		System.out.println("Email Address: "+secondEmail);
		System.out.println("All First Name: "+allUserNames);
		System.out.println("All Email Addresses: "+allEmailAddress);
	}
	
	// ─── REUSABLE HELPER METHOD ─────────────────────
	private HashMap<String, Object> buildPostBody(String title,String body, int userId){
		
		HashMap<String, Object> requestBody = new HashMap<>();
		requestBody.put("title", title);
		requestBody.put("body", body);
		requestBody.put("userId",userId);
		return requestBody;
		
	}
	

}
