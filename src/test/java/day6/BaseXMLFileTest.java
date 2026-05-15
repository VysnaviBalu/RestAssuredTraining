package day6;

import io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.BeforeClass;
import java.io.File;

public class BaseXMLFileTest {
	
	XmlPath xp;
	
	@BeforeClass
	void setup() {
		
	File myXML = new File("src/test/resources/stores.xml");
	xp = new XmlPath(myXML);
	
	}

}
