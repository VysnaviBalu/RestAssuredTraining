package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.ArrayList;




/* Different ways to create POST Request Body 
1. Post request body using HashMap
2. Post request body creation using ORG.JSON
3. Post request body creation using POJO
4. Post request using external file JSON Data */

public class DiffWaysToCreatePostBodyRequest extends BaseTest {
	
	
	@Test(priority=2)
	public void testPostUsingHashMap() {
	
	HashMap<String, Object> data = new HashMap<>();
	
	data.put("name", "Carlos");
	data.put("phone", "8765243109");
	data.put("location", "Spain");
	
	ArrayList<String> courseArray = new ArrayList<>();
	courseArray.add("C");
	courseArray.add("F1");
	data.put("courses", courseArray);
	
	System.out.println(data);
	
	given()
	 .contentType(ContentType.JSON)
	 .body(data)
	.when()
	 .post("/students")
	.then()
	 .statusCode(201)
	 .body("name", equalTo("Carlos"))
	 .body("location",equalTo("Spain"))
	 .body("phone",equalTo("8765243109"))
	 .body("courses[0]",equalTo("C"))
	 .body("courses[1]",equalTo("F1"))
	 .log().all();
	
	}
	
	
//	// ─── DELETE ─────────────────────────────────────
//		@Test (priority=1)
//		void deleteUser() {
//			given()
//			
//			.when()
//			 .delete("/students/dBs1kMlqWWc")
//			 
//			.then()
//			 .statusCode(200);
//		}

}
