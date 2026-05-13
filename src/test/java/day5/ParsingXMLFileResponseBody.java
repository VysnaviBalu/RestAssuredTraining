package day5;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import java.util.List;


public class ParsingXMLFileResponseBody extends BaseXMLTest{
	
	
	@Test(priority=1)
	void parsingXMLResponseBody() {
		
		System.out.println(xp.getString("stores.books.book[1].author"));
		System.out.println(xp.getList("stores.books.book.title"));
		
		// Verify Total Number of Books
		List<Integer> totalBooks = xp.getList("stores.books.book");
		Assert.assertEquals(totalBooks.size(),7);
		
		// Verify the Book Title
		List<String> bookTitle = xp.getList("stores.books.book.title");
		boolean status = false;
		for (String name : bookTitle) {
			if(name == "The Lord of the Rings") {
				System.out.println(name+ " is present in the book");
				status = true;
				break;
				
			}
		}
		
		Assert.assertEquals(status,true);
		
	}

}
