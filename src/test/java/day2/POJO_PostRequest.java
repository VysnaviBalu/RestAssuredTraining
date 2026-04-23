package day2;

import java.util.ArrayList;

public class POJO_PostRequest {
	
	String name;
	String location;
	String phone;
	ArrayList<String> courses = new ArrayList<>();
	String idValue;
	
	
	
	// getters
	
	public String getName() { return name; } 
	
	public String getLocation() { return location; }
	
	public String getPhone() { return phone;}
	
	public ArrayList<String> getCourses() { return courses; }
	
	public String getIdValue() { return idValue; }
	
	
	// setters
	public void setName(String name) { this.name = name; }
	
	public void setLocation(String location) { this.location = location; }
	
	public void setPhone(String phone) { this.phone = phone; }
	
	public void setCourses(ArrayList<String> courses) { this.courses = courses; }
	
	
	@Override
	public String toString() {
	    return "POJO_PostRequest{name='" + name + "', location='" + location + 
	           "', phone='" + phone + "', courses=" + courses + "}";
	}

}
