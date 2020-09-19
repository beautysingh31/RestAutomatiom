package Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.testng.annotations.Test;

import Pojos.Location;
import Pojos.addPlace;
import RestAutomation.RestAutomation.Payloads;
import RestAutomation.RestAutomation.ResuableCode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class serialisation 
{


	
		@Test
		public static void addplace()
		{
			RestAssured.baseURI="https://rahulshettyacademy.com/";
			//Post Request-Add a place and extract Placeid
			addPlace ad=new addPlace();
			ad.setAccuracy(12);
			ad.setAddress("123 street");
			ad.setLanguage("english");
			ad.setName("Mohit");
			ad.setPhone_number(8582);
			ad.setWebsite("xyz");
			Location lc=new Location();
			lc.setLng(12);
			lc.setLat(13);
			ad.setLocation(lc);
			ArrayList<String> al=new ArrayList<>();
			al.add("shoe park");
			al.add("shop");
			ad.setTypes(al);
			
		 ///Request specification
			RequestSpecification req=new RequestSpecBuilder().setBasePath("https://rahulshettyacademy.com/").
			addQueryParam("key","qaclick123").addHeader("Content-Type","application/json\\r\\n").setBody(ad).build();
			
			//Getting the given part in a variable
			RequestSpecification request=given().spec(req).log().all();
			
			//Response Specification
			ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).expectHeader("Server", "Apache/2.4.18 (Ubuntu)").expectBody("scope",equalTo("APP")).build();
			//passing the request to extract resposne
			Response resposne=request.when().post("maps/api/place/add/json").then().log().all().spec(resp).extract().response();
			String stresp=resposne.asString();
			
			
			
			/*addPlace resp=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
			body(ad).
			when().post("maps/api/place/add/json").
			then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").
			extract().response().as(addPlace.class);
			/*JsonPath response=ResuableCode.rawToJson(resp);
			String placeid=response.get("place_id");
			System.out.println("Placeid is ="+ placeid);*/
			
			System.out.println(stresp);
	}
}
