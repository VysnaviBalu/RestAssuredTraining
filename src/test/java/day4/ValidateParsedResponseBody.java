package day4;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.*;


public class ValidateParsedResponseBody extends BaseTest{
	
	@Test(priority=1)
	void testJsonResponseBody() {
		Response res = given()
				        .contentType(ContentType.JSON)
				       .when()
				        .get("/store");
		
		Assert.assertEquals(res.getStatusCode(),200);
		System.out.println(res.jsonPath().getString("greetingCards[1].occasion").equals("Wedding"));
		
		String bookName = res.jsonPath().getString("book[0].title");
		Assert.assertEquals(bookName, "Sayings of the Century");
				        
	}
	
	@Test(priority=1)
	void testJsonObjectClass() {
		Response res = given()
				        .contentType(ContentType.JSON)
				       .when()
				        .get("/store");
		
		JSONObject js = new JSONObject(res.asString()); // Converting response to JSON Object type
		
		for(String category : js.keySet()) {
			System.out.println(category);
			if(category.equalsIgnoreCase("book")) {
				for(int i = 0; i< js.getJSONArray(category).length();i++) {
					System.out.println(js.getJSONArray(category).getJSONObject(i).getString("title"));
				}
			}
		}

	  
		
	}

}
