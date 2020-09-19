package stepDefinition;
import static io.restassured.RestAssured.*;


import java.util.ArrayList;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.runner.RunWith;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import Pojos.Location;
import Pojos.addPlace;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestData;
import resources.enumResources;
import resources.utils;

@RunWith(Cucumber.class)

public class MyStepDefinitions extends utils{
	
	static RequestSpecification request;
	Response resposne;
	ResponseSpecification resp;
	TestData data=new TestData();
	String strResp;
	String placeid;

	 @Given("^Place payload and Query Param \"([^\"]*)\"  \"([^\"]*)\"  \"([^\"]*)\"$")
	    public void place_payload_and_query_param_something_something_something(String name, String lanuage,String address) throws Throwable 
	 

    {

      request=RestAssured.given().spec(requestSpecification()).body(data.addplace(name,lanuage,address));
    }
	 @When("^\"([^\"]*)\" is called with \"([^\"]*)\" http request$")
	    public void user_is_called_with_any_http_request(String resource, String method) throws Throwable
    {

		enumResources resour= enumResources.valueOf(resource);
		System.out.println(resour.resources());
		 if(method=="POST")
   	      resposne=request.when().post(resour.resources());
		 
		 else if(method=="GET")
		 
		 resposne=request.when().get(resour.resources());
		 
		 else if(method=="DELETE")
		
			 resposne=request.when().delete(resour.resources());
		 
    	//System.out.println(resposne);
		// resp=new ResponseSpecBuilder().expectContentType(ContentType.JSON).
		   		//	expectStatusCode(200).build(); 
    	
    }

	 @Then("^successful \"([^\"]*)\" in body is \"([^\"]*)\"$")
	    public void successful_something_in_body_is_something(String keyvalue, String expkeyvalue) throws Throwable
    {
    	String strResp=resposne.asString();
    	System.out.println(strResp);
    	JsonPath js=new JsonPath(strResp);
    	assertEquals(js.get(keyvalue).toString(), expkeyvalue);
    }

    @And("^\"([^\"]*)\" of the body is \"([^\"]*)\"$")
    public void something_of_the_body_is_something(String key, String expkey) throws Throwable
    {
    	String strResp=resposne.asString();
    	JsonPath js=new JsonPath(strResp);
    	assertEquals(js.get(""+key+"").toString(), expkey);
    }
    

    @And("^verify that Placeid created maps to \"([^\"]*)\"  using \"([^\"]*)\"$")
    public void verify_that_placeid_created_maps_to_something_using_something(String expname, String resource) throws Throwable
    {
    	 placeid=getJsonPath(resposne,"place_id");
    	request=given().spec(requestSpecification()).queryParam("place_id",placeid);
    	user_is_called_with_any_http_request(resource,"GET");
    	String actualname=getJsonPath(resposne,"name") ;
    	assertEquals(actualname, expname);
    	System.out.println(resposne);
    	System.out.println("Place id is"+ placeid);
    	
    	
    	
    }
    
    @Given("^place payload with placeid$")
    public void place_payload_with_placeid() throws Throwable {
       addPlace ad=new addPlace();
    	request=given().spec(requestSpecification()).body(addPlace.deletepayload());
    }


}