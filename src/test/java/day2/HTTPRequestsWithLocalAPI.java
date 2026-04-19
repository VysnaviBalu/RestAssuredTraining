package day2;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.testng.annotations.*;

import io.restassured.http.ContentType;

public class HTTPRequestsWithLocalAPI extends BaseTest {

	int idValue;

	@Test(priority = 1)
	void getStudents() {

		when().get("/students").then().statusCode(200).log().all();

		idValue = when().get("/students").then().statusCode(200).extract().jsonPath().getInt("id[0]");
		System.out.println(idValue);
	}

	
	@Test(priority=2, dependsOnMethods ={"getStudents"}) 
	 void updateStudents() {
	    given() .contentType(ContentType.JSON) .body(buildPostBody(idValue,"Vysnavi","Cucumber")) 
	    .when() .put("/students/"+idValue)
	    .then().statusCode(200) .log().all();
	  
	  }
	  
	 
	  // ─── REUSABLE HELPER METHOD ───────────────────── 
	 private HashMap<String,Object> buildPostBody(int id,String name, String course){
	 
	  HashMap<String, Object> requestBody = new HashMap<>(); 
	  requestBody.put("id",id); 
	  requestBody.put("name", name); 
	  requestBody.put("course",course); 
	  return requestBody;
	  
	  }
	 

}
