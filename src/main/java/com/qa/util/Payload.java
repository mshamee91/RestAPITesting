package com.qa.util;

public class Payload {
	public static String addPlace() {
		return "{\r\n" + 
				"    \"location\": {\r\n" + 
				"        \"lat\": -38.383494,\r\n" + 
				"        \"lng\": 33.427362\r\n" + 
				"    },\r\n" + 
				"    \"accuracy\": 50,\r\n" + 
				"    \"name\": \"API REST house 4\",\r\n" + 
				"    \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"    \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"    \"types\": [\r\n" + 
				"        \"shoe park\",\r\n" + 
				"        \"shop\"\r\n" + 
				"    ],\r\n" + 
				"    \"website\": \"http://google.com\",\r\n" + 
				"    \"language\": \"French-IN\"\r\n" + 
				"}";
	}
	
	public static String complexResponse() {
		return "{\r\n" + 
				"	\"dashboard\": {\r\n" + 
				"		\"purchaseAmount\": 910,\r\n" + 
				"		\"website\": \"rahulshettyacademy.com\"\r\n" + 
				"	},\r\n" + 
				"	\"courses\": [\r\n" + 
				"		{\r\n" + 
				"			\"title\": \"Selenium Python\",\r\n" + 
				"			\"price\": 50,\r\n" + 
				"			\"copies\": 6\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"title\": \"Cypress\",\r\n" + 
				"			\"price\": 40,\r\n" + 
				"			\"copies\": 4\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"title\": \"RPA\",\r\n" + 
				"			\"price\": 45,\r\n" + 
				"			\"copies\": 10\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
	}

public static String addBook(String isbn,String aisle) {
	return "{\r\n" + 
			"\r\n" + 
			"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
			"\"isbn\":\""+isbn+"\",\r\n" + 
			"\"aisle\":\""+aisle+"\",\r\n" + 
			"\"author\":\"Johnson\"\r\n" + 
			"}";
}
}
//{
//"dashboard": {
//	"purchaseAmount": 910,
//	"website": "rahulshettyacademy.com"
//},
//"courses": [
//	{
//		"title": "Selenium Python",
//		"price": 50,
//		"copies": 6
//	},
//	{
//		"title": "Cypress",
//		"price": 40,
//		"copies": 4
//	},
//	{
//		"title": "RPA",
//		"price": 45,
//		"copies": 10
//	}
//]
//}
 
