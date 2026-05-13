package day5;

import io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;

import java.io.File;

import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

public class BaseXMLTest {
	XmlPath xp ;
	
	@BeforeClass
	void setup() {
		File xmlFile = new File ("src/test/resources/stores.xml");
		 xp = new XmlPath(xmlFile);	
	}

}
