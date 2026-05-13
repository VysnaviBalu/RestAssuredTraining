package day5;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.xml.XmlPath;


public class ParsedXMLResponseBody extends BaseTest{
	
	@Test
	void getResponseFromXML() {
		
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
