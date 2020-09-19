package Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAutomation.RestAutomation.Payloads;
import RestAutomation.RestAutomation.ResuableCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddLibrary
{

	@Test(dataProvider="booksdata")
	public static void addbook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String adbook=given().header("Content-Type","application/json").body(Payloads.addbook(isbn,aisle)).when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ResuableCode.rawToJson(adbook);
		String id=js.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="booksdata")
	public Object[][] getdata()
	{
		Object[][] obj=new Object[][] {{"chotki","211"},{"biklu","678"},{"mohit","367"}};
		return obj;
	}
}
