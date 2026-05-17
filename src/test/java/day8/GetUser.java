package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.*;

public class GetUser extends BaseTest{
	
	
	@Test(dependsOnGroups = {"create"})
	void testGetUserData(ITestContext context) {
		
		System.out.println("******* Get User *******");
		
		 int userID = (int) context.getAttribute("id");
	     String userName = (String) context.getAttribute("name");
		
		given()
		 .header("Authorization","Bearer "+token)
		.when()
		 .get("/users/"+userID)
		.then()
		 .statusCode(200)
		 .body("id", equalTo(userID))
		 .body("name", equalTo(userName))
		 .log().all();
		
	}	

}
