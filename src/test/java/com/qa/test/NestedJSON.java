package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.JsonParser;
import com.qa.util.Payload;

import io.restassured.path.json.JsonPath;

public class NestedJSON {
	
	JsonParser js = new JsonParser();
	@Test
	public void validateResponse() {
		JsonPath jp = js.getResponse(Payload.complexResponse());
		
		//Print No of courses returned by API
		int courses_number = jp.getInt("courses.size()");
		System.out.println(courses_number);
		
		//Print Purchase Amount
		int purchaseAmount = jp.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//Print Title of the first course
		String title = jp.getString("courses[0].title");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<courses_number;i++) {
			String titles = jp.getString("courses["+i+"].title");
			int prices = jp.getInt("courses["+i+"].price");
			System.out.println(titles);
			System.out.println(prices);
		}
		
		
		//Print no of copies sold by RPA Course
		int copies = 0;
		for(int i=0;i<courses_number;i++) {
			String titles = jp.getString("courses["+i+"].title");
			if(titles.equalsIgnoreCase("RPA")) {
				copies = jp.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
			
		}
		
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int amount,sum = 0;
		for(int i=0;i<courses_number;i++) {
			int prices = jp.getInt("courses["+i+"].price");
			int copies1 = jp.getInt("courses["+i+"].copies");
			amount = prices * copies1;
			sum = sum + amount;
		}
		Assert.assertEquals(sum, purchaseAmount);
		
	}
	
}
