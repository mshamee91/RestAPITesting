package com.qa.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.qa.util.JsonParser;
import com.qa.util.Payload;

public class MapsAPI {
	
	public static void main(String[] args) throws IOException {
		JsonParser js = new JsonParser();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
				
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		
		//Add Place
		//Method 1 - getting payload
//		String Add_response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payload.addPlace())
//				.when().post("maps/api/place/add/json")
//		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();	
		
		//Method 2 - getting payload
		String Add_response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Shameer\\Selenium\\RestAPItesting\\src\\test\\java\\TestData\\AddPlace.json"))))
				.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();	
		
		System.out.println("Add response is "+Add_response);
		JsonPath jp = js.getResponse(Add_response) ;
		String place_id = jp.getString("place_id");
		System.out.println("place_id in response is "+place_id);
		
		//Update Place
		String new_address = "70 Summer walk, USA";
		String Update_response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"\"place_id\":\""+place_id +"\",\r\n" + 
				"\"address\":\""+ new_address +"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json")
				.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		System.out.println("Update response is "+Update_response);
		
		//Get Place		
		String Get_response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", ""+place_id+"")
				.when().get("maps/api/place/get/json")
				.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("Get response is "+Get_response);
		
		JsonPath jp1 = js.getResponse(Get_response) ;
		String actual_address = jp1.getString("address");
		System.out.println("address in response is "+actual_address);
		
		Assert.assertEquals(actual_address, new_address);
		
				
	}
}
