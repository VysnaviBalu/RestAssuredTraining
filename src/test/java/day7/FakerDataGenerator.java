package day7;

import static io.restassured.RestAssured.*;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	void testGenerateDummyData() {
		
		Faker faker = new Faker();
		
		String fulllName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String location =faker.nation().capitalCity();
		
		String userName = faker.name().username();
		String password = faker.internet().password(6, 8);
		String emailAddress = faker.internet().safeEmailAddress();
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		String animal = faker.animal().name();
		
		System.out.println("Full Name: "+fulllName);
		System.out.println("First Name: "+firstName);
		System.out.println("Last Name: "+lastName);
		System.out.println("Location: "+location);
		System.out.println("UserName: "+userName);
		System.out.println("Password: "+password);
		System.out.println("Email Address : "+emailAddress);
		System.out.println("Phone Number : "+phoneNumber);
		System.out.println("Fav Animal: "+animal);	
		
	}
	
	
	

}
