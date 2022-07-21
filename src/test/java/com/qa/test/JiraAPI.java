package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraAPI {
	
	//SessionFilter Authentication using filter method
	//Path and Query parameters
	//Adding attachment using Multipart method
	//Parsing complex JSON
	//Handling HTTPS certification validation
	
	@Test
	public void Jira() {
		SessionFilter session = new SessionFilter();
		RestAssured.baseURI = "http://localhost:8080";
		//Login to JIRA
		String login_response = given().log().all().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{\r\n" + 
				"    \"username\": \"RESTAPI\",\r\n" + 
				"    \"password\": \"practice00\"\r\n" + 
				"}").filter(session)
				.when().post("/rest/auth/1/session")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//Create an issue
		String create_response = given().log().all().header("Content-Type", "application/json").body("{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"RES\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"Visa1 card accepting alphabets\",\r\n" + 
				"        \"description\": \"Desc - Visa1 card accepting alphabets\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}").filter(session)
				.when().post("/rest/api/2/issue")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath jp = new JsonPath(create_response);
		String id = jp.getString("id");
		
		//Add Comment
		String comment = "Adding comments";
		String AddComment_response = given().log().all().pathParam("ID", id).header("Content-Type", "application/json").body("{\r\n" + 
				"    \"body\": \""+comment+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(session)
				.when().post("/rest/api/2/issue/{ID}/comment")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath jp2 = new JsonPath(AddComment_response);
		int comment_id = jp2.getInt("id");
		System.out.println(comment_id);
		
		//Add attachment
		String Addattachment_response = given().log().all().pathParam("ID", id).header("X-Atlassian-Token","no-check")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file",new File("attach.txt")).filter(session)
				.when().post("/rest/api/2/issue/{ID}/attachments")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//GET Issue
//		String Get_response = given().log().all().queryParam("fields", "comment").pathParam("key", "10005").filter(session)
//		.when().get("/rest/api/2/issue/{key}").
//		then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath jp1 = new JsonPath(Get_response);
//		int comments_size = jp1.getInt("fields.comment.comments.size()");
//		String actualcomment = "";
//		for(int i=0;i<comments_size;i++) {
//			int addedcomment_id = jp1.getInt("fields.comment.comments["+i+"].id");
//			if(addedcomment_id==comment_id) {
//				actualcomment = jp1.getString("fields.comment.comments["+i+"].body");
//				System.out.println(addedcomment_id+" "+i+" "+actualcomment);
//				break;
//			}
//		}
//		Assert.assertEquals(actualcomment, comment);
	
	}

}
