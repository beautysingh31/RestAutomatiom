package RestAutomation.RestAutomation;

import io.restassured.path.json.JsonPath;

public class ResuableCode
{
 public static JsonPath rawToJson(String response)
 {
	 JsonPath js1=new JsonPath(response);
	 return js1;
 }
}
