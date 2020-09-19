package resources;

public enum enumResources 
{

	AddPlaceApi("maps/api/place/add/json"),
	DeletePlaceApi("maps/api/place/delete/json"),
	GetPlaceApi("maps/api/place/get/json");
	 private String resource;
	 
	 enumResources( String resource) 
	{
		this.resource=resource;
	}
	 
	 public  String resources()
	 {
		 return resource;
	 }
}
