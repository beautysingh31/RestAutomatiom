package stepDefinition;

import io.cucumber.java.Before;


public class Hooks
{

	@Before("@Deleteplace")
	
	public String beforescenario() throws Throwable
	{
		MyStepDefinitions sd=new MyStepDefinitions();
		if(sd.placeid==null)
		{
		sd.place_payload_and_query_param_something_something_something("Shahpura", "Hindi", "Bhopal");
		sd.user_is_called_with_any_http_request("addPlaceAPI", "POST");
		sd.verify_that_placeid_created_maps_to_something_using_something("Shahpura", "getPlaceAPI");
		return sd.placeid;
		}
		return sd.placeid;
		
	}
	
	
	
}
