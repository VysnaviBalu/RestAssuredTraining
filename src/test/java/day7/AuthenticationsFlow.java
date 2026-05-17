package day7;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

public class AuthenticationsFlow {
	
	//@Test
	void testBasicAuthentication() {
		
		System.out.println("***** Basic Authentication ******");
		given()
		 .auth().basic("postman", "password")
		.when()
		 .get("https://postman-echo.com/basic-auth")
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();
		
	}
	
	@Test
	void testDigestAuthentication() {
		
		System.out.println("***** Digest Authentication ******");
		given()
		 .auth().digest("postman", "password")
		.when()
		 .get("https://postman-echo.com/basic-auth")
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();
	}
	
	
	//@Test
	void testPreemptiveAuthentication() {
		
		System.out.println("***** Preemptive Authentication ******");
		given()
		 .auth().preemptive().basic("postman", "password")
		.when()
		 .get("https://postman-echo.com/basic-auth")
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();
	}
	
	
    
	@Test 
	void testBearerToken() {
		
		System.out.println("***** Bearer Token ******");
		
		  String bearerToken = System.getenv("GITHUB_TOKEN");
		    System.out.println("Token: " + bearerToken); // add this
		    
		    given()
		     .header("Authorization", "Bearer " + bearerToken)
		    .when()
		     .get("https://api.github.com/user/repos")
		    .then()
		     .statusCode(200)
		     .log().all();
	}
	
	@Test
	void testAPIKeyAuthenticationAsQueryParam() {
		
		System.out.println("***** API Key in QueryParamater ******");
		
		String apiKey = System.getenv("NASA_API_KEY");
		
		System.out.println("API KEY: "+apiKey);
		
		given()
		 .queryParam("api_key",apiKey)
		.when()
		 .get("https://api.nasa.gov/planetary/apod")
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
	//@Test
	void testAPIKeyAuthenticationAsHeader() {
		
	}
	
	
	//No valid data to try with
	//@Test 
	void testOAuth1Authentication() {
		
		given()
		 .auth().oauth("consumerKey", "ConsumerSecret", "accessToken", "Token Secret")
		.when()
		 .get("url")
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
	
	//No valid data to try with
	//@Test 
	void testOAuth2Authentication() {
		
		given()
		 .auth().oauth2("token")
		.when()
		 .get("url")
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
}
