package Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAutomation.RestAutomation.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import RestAutomation.RestAutomation.ResuableCode;;


 class AddPlace {

	@Test
	public static void addplace()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Post Request-Add a place and extract Placeid
		
		String resp=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(Payloads.AddPlace()).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").
		extract().response().asString();
		JsonPath response=ResuableCode.rawToJson(resp);
		String placeid=response.get("place_id");
		System.out.println("Placeid is ="+ placeid);
		
		//Put Request-Update address using the Placeid extracted
		String expaddress="70 Summer walk, UK";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+expaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().put("maps/api/place/update/json").then().assertThat().statusCode(200).
		body("msg",equalTo("Address successfully updated"));
		
		//Get Request to fetch updated address
		String address=given().queryParam("key","qaclick123").queryParam("place_id",""+placeid+"").header("Content-Type","application/json").
		when().get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath updateaddr=ResuableCode.rawToJson(address);
		String newaddress=updateaddr.get("address");
		System.out.println("Update address is "+ newaddress);
		Assert.assertEquals(expaddress, newaddress);
		
		
		
	}

}
 