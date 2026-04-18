package day1;

import io.restassured.RestAssured;

import java.io.FileWriter;

import org.testng.annotations.BeforeClass;

import com.opencsv.CSVWriter;


public class BaseTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.useRelaxedHTTPSValidation(); 
		
		 // Clear output CSV before each run
        try {
            CSVWriter writer = new CSVWriter(
                new FileWriter("src/test/resources/responsedata.csv", false));
            writer.writeNext(new String[]{"id","title","body","userId","timestamp"});
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	

}
