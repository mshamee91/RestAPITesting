package com.qa.test;

import org.testng.annotations.Test;

import com.qa.util.Location;
import com.qa.util.Payloads;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

	@Test
	public void addPlace_Maps() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Payloads p = new Payloads();

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		p.setLocation(l);
		p.setAccuracy(50);
		p.setName("API REST house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen street 10");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		p.setTypes(types);

		Response res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(p).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract()
				.response();

		String response = res.asString();

		System.out.println(response);
	}

}
