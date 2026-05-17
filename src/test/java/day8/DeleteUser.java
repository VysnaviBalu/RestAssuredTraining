package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import io.restassured.http.ContentType;



public class DeleteUser extends BaseTest{
	CreateUser cu = new CreateUser();
	
	@Test
	void createUserdata() {
		cu.testCreateUser();
	}
	
	@Test(dependsOnMethods= {"createUserdata"})
	void testDeleteUserData() {
		
		System.out.println("******* Delete User *******");
		
		given()
		 .header("Authorization", "Bearer "+token)
		 .contentType(ContentType.JSON)
		.when()
		 .delete("/users/"+cu.userID)
		.then()
		 .statusCode(204)
		 .log().all();
		
		 System.out.println("***** API Chaining Complete! ******");
	        System.out.println("Created and deleted User ID: " + cu.userID);
	}

}
