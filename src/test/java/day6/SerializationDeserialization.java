package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SerializationDeserialization {
	
	@Test
	void convertPojoToJson() throws JsonProcessingException {
		
		System.out.println("************** Serialization *********** ");
		
        PojoBodyData pojo = new PojoBodyData();
		
		pojo.setName("Carlos");
		pojo.setLocation("Spain");
		pojo.setPhone("8765243109");
		
		
		ArrayList<String> courseArray = new ArrayList<>();
		courseArray.add("C");
		courseArray.add("F1");
		pojo.setCourses(courseArray);
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		System.out.println(jsonData);
		
	}
	
	
	@Test
	void convertJsonToPojo() throws JsonProcessingException {
		
		System.out.println("************** Deserialization *********** ");
		
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Carlos\",\n"
				+ "  \"location\" : \"Spain\",\n"
				+ "  \"phone\" : \"8765243109\",\n"
				+ "  \"courses\" : [ \"C\", \"F1\" ],\n"
				+ "  \"idValue\" : null\n"
				+ "}";
		
		ObjectMapper objMapper = new ObjectMapper();
		
		PojoBodyData pdb = objMapper.readValue(jsonData,PojoBodyData.class);
		
		System.out.println("Name "+pdb.getName());
		pdb.getLocation();
		pdb.getPhone();
		System.out.println(pdb.getCourses());
		
	}

}
