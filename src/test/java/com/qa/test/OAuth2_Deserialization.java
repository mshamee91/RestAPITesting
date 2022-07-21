package com.qa.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.GetCourses;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;

public class OAuth2_Deserialization {

	@Test
	public void getCourse() {
		
		String[] exp_courses = {"Selenium Webdriver Java","Cypress","Protractor"};
		String[] exp_prices = {"50","40","40"};
//		System.setProperty("webdriver.chrome.driver","C:\\Shameer\\Selenium\\RestAPItesting\\src\\test\\resources\\WebDrivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=https://rahulshettyacademy.com/getCourse.php&response_type=code&auth_uri=https://accounts.google.com/o/oauth2/v2/auth&scope=https://www.googleapis.com/auth/userinfo.email&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("cucumberpractice00@gmail.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("practice00");
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
//		String url = driver.getCurrentUrl();
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWidT8d8A1e1IkyS35Vy9RnixgD8Ne_Xomf7JVbPCXB6FHAhu1Id_U2bFUF4WHpgJQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String[] split1 = url.split("code=");
		String[] split2 = split1[1].split("&scope");
		String code = split2[0];
		
		String accesstoken_response = given().log().all().urlEncodingEnabled(false).queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("grant_type", "authorization_code").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accesstoken_response);
		JsonPath jp1 = new JsonPath(accesstoken_response);
		String access_token = jp1.getString("access_token");
		
		GetCourses get_response = given().log().all().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().assertThat().statusCode(200).extract().response().as(GetCourses.class);
		
		System.out.println(get_response);
		System.out.println(get_response.getExpertise());
		System.out.println(get_response.getInstructor());
		System.out.println(get_response.getUrl());
		System.out.println(get_response.getServices());
		System.out.println(get_response.getLinkedIn());
		int automation_size = get_response.getCourses().getWebAutomation().size();
		
		ArrayList<String> act_courses = new ArrayList<String>();
		ArrayList<String> act_prices = new ArrayList<String>();
		for(int i=0;i<automation_size;i++) {
			String courseTitle = get_response.getCourses().getWebAutomation().get(i).getCourseTitle();
			String price = get_response.getCourses().getWebAutomation().get(i).getPrice();
			System.out.println(courseTitle);
			System.out.println(price);
			act_courses.add(courseTitle);
			act_prices.add(price);
		}
		
		Assert.assertTrue(Arrays.asList(exp_courses).equals(act_courses));
		Assert.assertTrue(Arrays.asList(exp_prices).equals(act_prices));		
		
		
	}
	
}
