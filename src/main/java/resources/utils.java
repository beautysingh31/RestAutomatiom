package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.cucumber.core.resource.Resource;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils 
{
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("looging.txt"));
		
		 RequestSpecification req=new RequestSpecBuilder().setBaseUri(globalproperties("BaseURI")).addQueryParam("key","qaclick123").setContentType(ContentType.JSON).
	    		
	    		 addFilter(RequestLoggingFilter.logRequestTo(log))
	    		 .addFilter(ResponseLoggingFilter.logResponseTo(log)).
	    		 build();
		 return req;
		}
		else
			return req;
	    		 
	 }
	
	public static String globalproperties(String key) throws IOException
	{
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\sdeep\\eclipse-workspace\\RestAutomation\\src\\main\\java\\resources\\global.properties");
		prop.load(fis);
		
		return prop.getProperty(key);
		
		
	}
	
	public static String getJsonPath(Response res,String key)
	
	{
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		String keyvalue=js.get(key).toString();
		return keyvalue;
	}
	

}
