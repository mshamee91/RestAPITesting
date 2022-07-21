package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.JsonParser;
import com.qa.util.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class LibraryAPI {
	JsonParser js = new JsonParser();
	@Test(dataProvider = "testdata")
	public void AddBookAPI(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json").body(Payload.addBook(isbn, aisle))
				.when().post("Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Add book response is "+response);
		
		JsonPath jp1 = js.getResponse(response) ;
		String actual_msg = jp1.getString("Msg");
		System.out.println("Msg in response is "+actual_msg);
		
		Assert.assertEquals(actual_msg, "successfully added");
	}

	@DataProvider(name = "testdata")
	public Object[][] getData() {
		return new Object[][] { { "land2", "20001" }, { "earth2", "20002" }, { "water2", "20003" } };

	}
}
