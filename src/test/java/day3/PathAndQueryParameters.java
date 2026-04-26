package day3;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import org.hamcrest.*;


public class PathAndQueryParameters extends BaseTest {
	
	@Test
	public void testUsingPathAndQueryParameters() {
		
		given()
		 .pathParam("myPath",5)
		 .queryParam("postId",1)
		 .queryParam("id",1)
		.when()
		 .get("/comments/{myPath}")
		.then()
		 .statusCode(200)
		 .log().all();
		 
	}
	
}