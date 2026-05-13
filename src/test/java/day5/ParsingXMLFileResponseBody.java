package day5;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLFileResponseBody extends BaseXMLTest{
	
	
	@Test(priority=1)
	void parsingXMLResponseBody() {
		
		System.out.println(xp.getString("stores.books.book[1].author"));
		System.out.println(xp.getList("stores.books.book.title"));
		
	}

}
