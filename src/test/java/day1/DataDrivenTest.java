package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataDrivenTest extends BaseTest {

    // ─── READ CSV PATH ──────────────────────────────
	String inputCSV  = System.getProperty("user.dir") 
            + "/src/test/resources/postdata.csv";
    String outputCSV = System.getProperty("user.dir") 
            + "/src/test/resources/responsedata.csv";
    
 // ─── GET CURRENT TIMESTAMP ──────────────────────────────
    String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // ─── DATA PROVIDER reads CSV ────────────────────
    @DataProvider(name = "postData")
    public Object[][] readCSV() throws Exception {

        CSVReader reader = new CSVReader(new FileReader(inputCSV));
        List<String[]> rows = reader.readAll();
        reader.close();

        // Remove header row
        rows.remove(0);

        Object[][] data = new Object[rows.size()][3];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i)[0]; // title
            data[i][1] = rows.get(i)[1]; // body
            data[i][2] = rows.get(i)[2]; // userId
        }
        return data;
    }

    // ─── POST with CSV data ──────────────────────────
    @Test(dataProvider = "postData")
    public void createPostFromCSV(
                    String title, String body, String userId) throws Exception {

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("userId", Integer.parseInt(userId));

        Response response =
            given()
                .contentType(ContentType.JSON)
                .body(requestBody)
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
}