package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import io.restassured.response.*;



public class UpdateUser extends BaseTest{
	
	CreateUser cu = new CreateUser();
	
	String name , email;
	
	
	@Test
	void createUserData() {
		cu.testCreateUser();
		System.out.println("The original name: " +cu.name);
		System.out.println("The original email: "+cu.email);
	}
	
	@Test(dependsOnMethods= {"createUserData"})
	void testUpdateUser() {
		
		System.out.println("******* Update User *******");

	    JSONObject jo = new JSONObject();
	    jo.put("name", faker.name().firstName());
	    jo.put("email", faker.internet().emailAddress());

	    Response updateData = given()
	     .header("Authorization", "Bearer " + token)
	     .contentType(ContentType.JSON)
	     .body(jo.toString())
	    .when()
	     .put("/users/" + cu.userID)
	    .then()
	     .statusCode(200)
	     .extract()
	     .response();

	    String updatedName = updateData.jsonPath().getString("name");
	    String updatedEmail = updateData.jsonPath().getString("email");

	    System.out.println("The updated name: " + updatedName);
	    System.out.println("The updated email: " + updatedEmail);
	}
	

}
