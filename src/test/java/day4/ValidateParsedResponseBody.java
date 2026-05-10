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
	void testJsonObjectClass() throws Exception {
		Response res = given()
				        .contentType(ContentType.JSON)
				       .when()
				        .get("/store");
		
		JSONObject js = new JSONObject(res.asString()); // Converting response to JSON Object type
		String bookTitle = null;
		boolean findBook = false;
		
		for(String category : js.keySet()) {
			if(category.equalsIgnoreCase("book")) {
				System.out.println(category);
				for(int i = 0; i< js.getJSONArray(category).length();i++) {
					 bookTitle = js.getJSONArray(category).getJSONObject(i).getString("title");
					if(bookTitle.equalsIgnoreCase("Sayings of the Century")){
						findBook = true;
						System.out.println("Sayings of the Centuryis present in the list");
						break;
						
					}
						
				}
			}
		}
		Assert.assertTrue(findBook, "Sayings of the Century not found in book list!");
		
	}

	  @Test(priority=1)
	  void getTotalPriceOfAllBooks() {
		  double totalPrice = 0;
		  Response res = given()
				       
				         .when()
				          .get("/store");
		  JSONObject js = new JSONObject(res.asString());
		  
		   for(String category : js.keySet()) {
			   if(category.equalsIgnoreCase("book")) {
				   for(int i = 0; i < js.getJSONArray(category).length(); i++) {
					   totalPrice = totalPrice + js.getJSONArray(category).getJSONObject(i).getDouble("price");
				   }
				   
			   }
		   }
		   
		   System.out.println("Total Price of the books: "+totalPrice);
		  
		  
	  }
	  
	  @Test(priority=1)
	  void getTotalPriceOfAllItems() {
		  double totalPrice = 0;
		  Response res = given()
				       
				         .when()
				          .get("/store");
		  JSONObject js = new JSONObject(res.asString());
		  
		   for(String category : js.keySet()) {
				   for(int i = 0; i < js.getJSONArray(category).length(); i++) {
					   totalPrice = totalPrice + js.getJSONArray(category).getJSONObject(i).getDouble("price");
				   }			   
		   }
		   
		   System.out.println("Total Price of All the Items: "+totalPrice);
	  }

}
