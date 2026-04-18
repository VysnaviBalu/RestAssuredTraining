
package day1;
//Core Rest Assured static imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

//Hamcrest matchers (for assertions)
import static org.hamcrest.Matchers.*;

//TestNG
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class HTTPResponse {
	
	@Test
	void getUser() {
		
		when()
		 .get("https://jsonplaceholder.typicode.com/users")
		 
		.then()
		 .statusCode(200)
		 .body("size()", equalTo(10));
		 
	}
	
	@Test
	void createUser() {
		String body = """
				{
				 "title" : "Awesomeness",
				 "body" : "Learning RestAssured",
				 "userId" : 1
				} 
				""";
		
		given()
		 .contentType(ContentType.JSON)
		 .body(body)
		 
		.when() 
		  .post("https://jsonplaceholder.typicode.com/posts")
		
		.then()  
		 .statusCode(201)
		 .log().all();
		 	 
				
	}
	
	@Test
	void putUser() {
		String body = """
				{
				 "title" :"AwesomeME",
				 "body" : "Learning To Code",
				 "userID" : 1
				}
				""";
		
		given()
		 .contentType(ContentType.JSON)
		 .body(body)
		
		.when()
		 .put("https://jsonplaceholder.typicode.com/posts/1")
		 
		.then()
		 .statusCode(200)
		 .log().all();
		
	}
	
	@Test
	void deleteUser() {
		given()
		
		.when()
		 .delete("https://jsonplaceholder.typicode.com/posts/1")
		 
		.then()
		 .statusCode(200)
		 .log().all();
	}

}
