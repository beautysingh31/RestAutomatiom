package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import RestAutomation.RestAutomation.ResuableCode;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class OAuth_2 
{

	
	@Test 	
	
	public static void getcourses()
	{
		/*String key="";
		String value="";
		System.setProperty(key, value);
		WebDriver driver=new ChromeDriver();*/
		String code="4%2F3wH1g-JyCZcchlEnoMdgs_N4fbIPbI3vz6eCxs9Rp8PbgTrar_9xilNHlnB84nD4dbadsTf8IulU6qe-BqWOjv0";
		//String partialcode=(url.split("code="))[1];
		//String code=partialcode.split("&scope")[0];
		System.out.println("code is"+code);
		
		//Exchange code
		
		String resp=given().urlEncodingEnabled(false).queryParam("code",""+code+"").
		queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParam("grant_type","authorization_code").when().post("https://www.googleapis.com/oauth2/v4/token").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=ResuableCode.rawToJson(resp);
		String accessToken=js.getString("access_token");
		
		//GetCourses
		
		String response=given().contentType("application/json").queryParam("access-token", accessToken).
				expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").then().statusCode(200).log().all().extract().response().asString();
		
		System.out.println("Response is courses"+ response);
	}
}
