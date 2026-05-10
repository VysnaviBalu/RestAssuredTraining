package day4;

import static io.restassured.RestAssured.*;

import java.util.*;

import org.testng.annotations.*;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;



public class ParsingResponseBody extends BaseTest {
	
	@Test(priority=1)
	void parsingResponseBodyApproach1() {
		
		System.out.println("******* Parsing Response Body - Single Item in One Category ***********");
		
		JsonPath jp =  given()
		                .contentType(ContentType.JSON)
		               .when()
		                .get("/store")
		               .then()
		                .statusCode(200)
		                .body("book[0].author",equalTo( "Nigel Rees"))
		                .extract()
		                .jsonPath();
		
		System.out.println(jp.getString("book[1].author"));
		System.out.println(jp.getList("book.author"));
		
		Map<String,Object> books = jp.getMap("book[0]");
		books.forEach((key,value) -> System.out.println(key+" "+value));
		System.out.println("*******************");
		
	}
	
	@Test(priority=1)
	void parsingResponseBodyApproach2() {
		
		System.out.println("******* Parsing Response Body Approach - All items in One Category  ***********");
		JsonPath jp = given()
				      .contentType(ContentType.JSON) 
				      .when()
				        .get("/store")
				      .then()
				        .statusCode(200)
				        .extract()
				        .jsonPath();
		
		List<Map<String, Object>> books = jp.getList("book");
		
		
		
		for(Map<String,Object> book : books) {
			System.out.println("***** Book ******");
			System.out.println(book.get("author"));
			System.out.println(book.get("category"));
			System.out.println(book.get("price"));
			System.out.println(book.get("title"));
		}		
		
	}
	
	@Test(priority=1)
	void parsingResponseBodyApproach3() {
		
		System.out.println("******* Parsing Response Body Approach - All items in All Categories  ***********");
		
		JsonPath jp = given()
				      .contentType(ContentType.JSON)
				      .when()
				       .get("/store")
				       
				      .then()
				       .statusCode(200)
				       .extract()
				       .jsonPath();
		
		
		
	    Map<String,List<Map<String, Object>>> store = jp.getMap("");
	    
	    System.out.println("Number of Catgories within the stores.json"+ store.size());
	    
	    for(String category: store.keySet()) {
	    	System.out.println("=======================================");
	    	System.out.println("Category:- "+category.toUpperCase());
	    	
	    	List<Map<String, Object>> items = store.get(category);
	    	System.out.println("Number of items in "+category+ " "+items.size());
	    	
	    	for(Map<String, Object> item : items) {
	    	item.forEach((key, value)-> System.out.println(key+ " "+value));
	    	}
	    }	
		
	}

}
