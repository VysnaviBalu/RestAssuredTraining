package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.*;
import io.restassured.http.ContentType;



public class DeleteUser extends BaseTest{
	
	@Test(dependsOnGroups = {"create"})
	void testDeleteUserData(ITestContext context) {
		
		System.out.println("******* Delete User *******");
		
		int userID = (int) context.getAttribute("id");
		
		given()
		 .header("Authorization", "Bearer "+token)
		 .contentType(ContentType.JSON)
		.when()
		 .delete("/users/"+userID)
		.then()
		 .statusCode(204)
		 .log().all();
		
		 System.out.println("***** API Chaining Complete! ******");
	     System.out.println("Created and deleted User ID: " + userID);
	}

}
