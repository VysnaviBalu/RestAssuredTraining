package day5;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;

import java.io.File;


public class TestFileUploadDownload extends BaseTestFile{
	
	@Test(priority=1)
	void uploadSingleFile() {
		
		File myFile = new File ("src/test/resources/stores.xml");
		
		given()
		  .multiPart("file", myFile)
		  .contentType("multipart/form-data")
		.when()
		  .post("/upload")
		.then()
		  .statusCode(200)
		  .body("filename",equalTo("stores.xml"))
		  .body("message",equalTo("File uploaded successfully!"))
		  .log().all();	
		
	}
	
	//@Test
	void uploadMultipleFiles() {
		File myFile1 = new File ("src/test/resources/stores.xml");
		File myFile2 = new File ("src/test/resources/body.json");
		
		given()
		 .multiPart("file", myFile1)
		 .multiPart("file", myFile2)
		.when()
		 .post("/upload-multiple")
		.then()
		 .statusCode(200)
		 .body("files[0].filename", equalTo("stores.xml"))
		 .body("files[1].filename", equalTo("body.json"))
		 .log().all(); 
	}
	
    @Test(priority=2)
    void downloadFile() {
    	given()
    	.when() 
    	 .get("/download/stores.xml")
    	.then()
    	 .statusCode(200)
    	 .log().all();
    }
	
	

}
