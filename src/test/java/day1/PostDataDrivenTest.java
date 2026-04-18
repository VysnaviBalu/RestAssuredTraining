package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class PostDataDrivenTest extends BaseTest{
	
	 // ─── READ CSV PATH ──────────────────────────────
	
	String inputCSV = System.getProperty("user.dir")
			+"/src/test/resources/postdata.csv";
	String outputCSV = System.getProperty("user.dir")
			+"/src/test/resources/responsedata.csv";
	
	 // ─── GET CURRENT TIMESTAMP ──────────────────────────────
	
	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	
	// ─── DATA PROVIDER reads CSV ────────────────────
	
	@DataProvider(name = "postData")
	public Object[][] readsCSV () throws Exception {
		CSVReader csvReader = new CSVReader(new FileReader(inputCSV));
		List<String[]> rows = csvReader.readAll();
		csvReader.close();
		
		rows.remove(0); // Remove the header
		
		Object[][] data = new Object[rows.size()][rows.get(0).length];
		for (int i = 0; i < rows.size(); i++) {
		    for (int j = 0; j < rows.get(i).length; j++) {
		        data[i][j] = rows.get(i)[j];
		    }
		  }
		return data;
		}
	
	@Test(dataProvider = "postData")
	public void createPostFromCSV(String title, String body, String userId) throws Exception {
		Response response =
				given()
				 .contentType(ContentType.JSON)
				 .body(buildPostBody( title,  body,  userId))
				.when()
				 .post("/posts")
				.then()
				 .statusCode(201)
				 .extract()
				 .response();
		 // Extract response fields
        String id         = response.jsonPath().getString("id");
        String resTitle   = response.jsonPath().getString("title");
        String resBody    = response.jsonPath().getString("body");
        String resUserId  = response.jsonPath().getString("userId");

        System.out.println("Created Post → id: " + id 
                         + " title: " + resTitle);
        System.out.println("Output CSV Path: " + outputCSV);

        // ─── WRITE to output CSV ─────────────────────
        CSVWriter writer = new CSVWriter(new FileWriter(outputCSV, true));
        writer.writeNext(new String[]{id, resTitle, resBody, resUserId, timestamp});
        writer.close();
		
	}
	
	// ─── REUSABLE HELPER METHOD ─────────────────────
	
	private HashMap<String,Object> buildPostBody (String title, String body, String userId){
		HashMap<String, Object> responseBody = new HashMap<>();
		responseBody.put("title", title);
		responseBody.put("body", body);
		responseBody.put("userId", Integer.parseInt(userId));
		
		return responseBody;
		
	}
	
	

}
