package com.qa.test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.JsonParser;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class HashMapTest {
	JsonParser js = new JsonParser();
	@Test
	public void AddBookAPI() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("name","Peter");
		map.put("isbn","isbn00");
		map.put("aisle","300");
		map.put("author","Henry");

		String response = given().log().all().header("Content-Type","application/json").body(map)
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Add book response is "+response);
		
		JsonPath jp1 = js.getResponse(response) ;
		String actual_msg = jp1.getString("Msg");
		System.out.println("Msg in response is "+actual_msg);
		
		Assert.assertEquals(actual_msg, "successfully added");
	}
}
