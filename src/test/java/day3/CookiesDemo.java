package day3;

import static io.restassured.RestAssured.*;
import io.restassured.response.*;
import org.testng.annotations.*;
import org.hamcrest.Matchers;

import java.util.Map;



public class CookiesDemo extends BaseTest{
	
	//@Test
	public void getCookies() {
		
		Map<String, String> cookieValue = given()
		
		.when()
		 .get("https://www.google.com")
		.then()
		 .statusCode(200)
		 .extract()
		  .cookies();
		
		System.out.println(cookieValue);

	}
	
	@Test
	public void getEntireResponse() {
		Response response  = given()
				   
				   .when()
				   .get("https://www.google.com");
		
		System.out.println(response.asString());
		System.out.println("******************");
		System.out.println(response.getStatusCode());
		System.out.println("******************");
		System.out.println(response.getCookies());
		System.out.println("******************");
		
		Map <String, String> cookie_values = response.getCookies();
		
		
		
		for(String key : cookie_values.keySet()) {
			System.out.println("Cookie Name: "+key);
			System.out.println("Cookie Value: "+cookie_values.get(key));
			System.out.println("******************");		
		}
		
		
		cookie_values.forEach((key, value)-> 
		    System.out.println(key+ " = "+value));
		
				   
					
	}

}
