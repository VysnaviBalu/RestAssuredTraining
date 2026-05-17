package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.*;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import io.restassured.response.*;



public class UpdateUser extends BaseTest{
	
	
	
	@Test(dependsOnGroups = {"create"})
	void testUpdateUser(ITestContext context) {
		
		System.out.println("******* Update User *******");
		
		 int userID = (int) context.getAttribute("id");

	    JSONObject jo = new JSONObject();
	    jo.put("name", faker.name().firstName());
	    jo.put("email", faker.internet().emailAddress());

	    Response updateData = given()
	     .header("Authorization", "Bearer " + token)
	     .contentType(ContentType.JSON)
	     .body(jo.toString())
	    .when()
	     .put("/users/" + userID)
	    .then()
	     .statusCode(200)
	     .extract()
	     .response();

	 // Update context with new values
        context.setAttribute("userName", updateData.jsonPath().getString("name"));
        context.setAttribute("userEmail", updateData.jsonPath().getString("email"));

        System.out.println("Updated Name: " + context.getAttribute("userName"));
        System.out.println("Updated Email: " + context.getAttribute("userEmail"));
	}
	

}
