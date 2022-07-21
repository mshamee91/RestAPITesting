package com.qa.util;

import io.restassured.path.json.JsonPath;

public class JsonParser {
	public JsonPath getResponse(String response) {
		JsonPath jp = new JsonPath(response);
		return jp;
		
	}
}
