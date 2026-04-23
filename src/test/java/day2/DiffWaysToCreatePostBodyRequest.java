package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.JSONObject;



/* Different ways to create POST Request Body 
1. Post request body using HashMap
2. Post request body creation using ORG.JSON
3. Post request body creation using POJO
4. Post request using external file JSON Data */

public class DiffWaysToCreatePostBodyRequest extends BaseTest {
	
	String idValue;
	
	// ─── Post using HashMap ─────────────────────────────────────
	
	@Test(priority=1)
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
	
	idValue = given()
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
	 .extract()
	 .jsonPath().getString("id");
	
	System.out.println(idValue);
	
	}
	
	// ─── Post using JSON Object ─────────────────────────────────────
	
	@Test(priority=1)
	public void testPostUsingJSONObject() {
	
	JSONObject data = new JSONObject();
	data.put("name", "Carlos");
	data.put("phone", "8765243109");
	data.put("location", "Spain");
	
	ArrayList<String> courseArray = new ArrayList<>();
	courseArray.add("C");
	courseArray.add("F1");
	data.put("courses", courseArray);
	
	System.out.println(data);
	
	idValue = given()
	 .contentType(ContentType.JSON)
	 .body(data.toString())
	.when()
	 .post("/students")
	.then()
	 .statusCode(201)
	 .body("name", equalTo("Carlos"))
	 .body("location",equalTo("Spain"))
	 .body("phone",equalTo("8765243109"))
	 .body("courses[0]",equalTo("C"))
	 .body("courses[1]",equalTo("F1"))
	 .extract()
	 .jsonPath().getString("id");
	
	System.out.println(idValue);
	
	}
	
	// ─── Post using POJO Class ─────────────────────────────────────
	
	@Test(priority=1)
		public void testPostUsingPOJOClass() {
			
		POJO_PostRequest pojo = new POJO_PostRequest();
		
		pojo.setName("Carlos");
		pojo.setLocation("Spain");
		pojo.setPhone("8765243109");
		
		
		ArrayList<String> courseArray = new ArrayList<>();
		courseArray.add("C");
		courseArray.add("F1");
		pojo.setCourses(courseArray);
		
		System.out.println(pojo);
		
		idValue = given()
		 .contentType(ContentType.JSON)
		 .body(pojo)
		.when()
		 .post("/students")
		.then()
		 .statusCode(201)
		 .body("name", equalTo("Carlos"))
		 .body("location",equalTo("Spain"))
		 .body("phone",equalTo("8765243109"))
		 .body("courses[0]",equalTo("C"))
		 .body("courses[1]",equalTo("F1"))
		 .extract()
		 .jsonPath().getString("id");
		
		System.out.println(idValue);
		
		}
		
	// ─── Post using external JSON file ─────────────────────────────────────	
		@Test (priority=1)
		public void testPostUsingExternalJsonFile() {
			File f = new File ("src/test/resources/body.json");
			
			idValue = given()
					   .contentType(ContentType.JSON)
					   .body(f)
					  .when()
					   .post("/students")
					  .then() 
					   .statusCode(201)
					   .body("name", equalTo("Carlos"))
						 .body("location",equalTo("Spain"))
						 .body("phone",equalTo("8765243109"))
						 .body("courses[0]",equalTo("C"))
						 .body("courses[1]",equalTo("F1"))
						 .extract()
						 .jsonPath().getString("id");
						
						System.out.println(idValue);
					 
		}
		
		
	
	// ─── DELETE ─────────────────────────────────────
		//@Test (priority=2)
		void deleteUser() {
			given()
			
			.when()
			 .delete("/students/"+idValue)
			 
			.then()
			 .statusCode(200);
		}
		
		@Test
		public void cleanupJunkEntries() {
		    
		    String[] junkIds = {"mHDiSzz6lC8"}; // 👈 your junk IDs
		    
		    for (String id : junkIds) {
		        given()
		        .when()
		            .delete("/students/" + id)
		        .then()
		            .statusCode(200);
		        
		        System.out.println("Deleted: " + id);
		    }
		}

}
