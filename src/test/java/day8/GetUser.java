package day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

public class GetUser extends BaseTest{
	
	CreateUser cu = new CreateUser();
	
	@Test
	void createUserFirst() {
		cu.testCreateUser();
	}
	
	@Test(dependsOnMethods= {"createUserFirst"})
	void testGetUserData() {
		
		System.out.println("******* Get User *******");
		
		given()
		 .header("Authorization","Bearer "+token)
		.when()
		 .get("/users/"+cu.userID)
		.then()
		 .statusCode(200)
		 .body("id", equalTo(cu.userID))
		 .body("name", equalTo(cu.name))
		 .log().all();
		
	}	

}
