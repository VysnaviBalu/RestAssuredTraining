package day5;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.*;


public class ParsedXMLResponseBody extends BaseTest{
	
	
	
	@Test(priority=1)
	void getResponseFromXMLApproach1() {
		
		System.out.println("*************** Approach 1 ***************");
		given()
		
		.when()
		 .get("/xml/note.xml")
		.then()
		 .statusCode(200)
		 .body("note.to",equalTo("Tove"))
		 .body("note.from",equalTo("Jani"))
		 .body("note.heading",equalTo("Reminder"))
		 .body("note.body",equalTo("Don't forget me this weekend!")); 		
		 
	}
	
	
	@Test(priority=2)
	void getResponseFromXMLApproach2() {
		
		System.out.println("*************** Approach 2 ***************");
		Response res = given()
				
				       .when()
				         .get("/xml/note.xml");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		System.out.println(res.getHeaders().toString());
		System.out.println(res.getContentType().toString());
		System.out.println(res.getCookies().toString());
		
		String noteTo = res.xmlPath().getString("note.to");
		Assert.assertEquals(noteTo, "Tove");		         
		
	}
	
	
	@Test(priority=2)
	void getResponseFromXMLApproach3() {
		
		System.out.println("*************** Approach 3 ***************");
		
	    XmlPath xp = given()
				
				       .when()
				        .get("/xml/note.xml")
				       .then()
				        .statusCode(200)
				        .extract()
				        .xmlPath();
	 
	 System.out.println(xp.getString("note.to"));
	 System.out.println(xp.getString("note.from"));
	 System.out.println(xp.getString("note.heading"));
	 System.out.println(xp.getString("note.body"));
	}

}
