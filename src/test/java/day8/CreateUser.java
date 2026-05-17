package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.*;
import io.restassured.response.*;
import io.restassured.http.ContentType;

import org.json.JSONObject;


public class CreateUser extends BaseTest{
	
	int userID = 0;
	String name, gender, email, status;
	
	@Test(groups = {"create"}) 
	void testCreateUser(ITestContext context) {
		
		System.out.println("******* Create User *******");
		
		JSONObject userData = new JSONObject();
		
		userData.put("name", faker.name().firstName());
		userData.put("gender", "Female");
		userData.put("email", faker.internet().emailAddress());
		userData.put("status", "inactive");
		
		Response createUserData = given()
		 .header("Authorization","Bearer "+token)
		 .contentType(ContentType.JSON)
		 .body(userData.toString())
		.when()
		 .post("/users")
		.then()
		 .statusCode(201)
		 .extract()
		 .response();
		
		context.setAttribute("id", createUserData.jsonPath().getInt("id"));
		System.out.println("The New User Created with ID: "+context.getAttribute("id"));	
		context.setAttribute("name", createUserData.jsonPath().getString("name"));
		System.out.println("The New User Created with Name: "+context.getAttribute("name"));
		context.setAttribute("gender", createUserData.jsonPath().getString("gender"));
		System.out.println("The New User Created with Gender: "+context.getAttribute("gender"));
		context.setAttribute("email", createUserData.jsonPath().getString("email"));
		System.out.println("The New User Created with Email: "+context.getAttribute("email"));
		context.setAttribute("status", createUserData.jsonPath().getString("status"));
		System.out.println("The New User Created with Status: "+context.getAttribute("status"));	
		
	}

}
