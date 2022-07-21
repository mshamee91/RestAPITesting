package com.qa.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.util.Location;
import com.qa.util.Payloads;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	@Test
	public void specB(){
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
		p.setAddress("29, side layout, cohen street 11");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		p.setTypes(types);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().log().all().spec(req).body(p);
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		Response response = request
				.when().post("/maps/api/place/add/json")
				.then().spec(res).extract().response();

		//		Response res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//				.body(p).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract()
//				.response();

		String response_str = response.asString();

		System.out.println(response_str);
	}
}
