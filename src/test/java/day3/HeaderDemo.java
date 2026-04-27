package day3;

import static io.restassured.RestAssured.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.*;
import org.testng.annotations.*;
import org.hamcrest.*;

import java.util.Map;
public class HeaderDemo extends BaseTest{
	
	@Test
	public void getHeader() {
		
		given()
		
		.when()
		 .get("https://www.google.com")
		.then()
		 .log().headers();
		
		System.out.println("*******************");

		
		Response response = given()
				            .when()
				             .get("https://www.google.com");
		
		System.out.println(response.getHeaders());
		System.out.println("*******************");
		
		System.out.println(response.header("Content-type"));
		System.out.println("*******************");
		
		System.out.println(response.header("server"));
		System.out.println("*******************");
		
		
		
		Headers headers = response.getHeaders();
		for (Header header: headers){
			System.out.println(header.getName()+ " = " +header.getValue());
			
		}
	
		
				             
	}
	

}
