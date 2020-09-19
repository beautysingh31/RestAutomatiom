package webservices;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;


 class postaddPlace {

	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Manjumil123456\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09131313\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://yahoo.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"").when().post("maps/api/place/add/json").then().assertThat().statusCode(200);
	}

}
