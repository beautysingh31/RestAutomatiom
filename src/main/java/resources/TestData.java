package resources;

import java.util.ArrayList;

import Pojos.Location;
import Pojos.addPlace;

public class TestData
{

	public addPlace addplace(String name, String lanuage, String address)
	{
    	addPlace ad=new addPlace();
		ad.setAccuracy(12);
		ad.setAddress(address);
		ad.setLanguage(lanuage);
		ad.setName(name);
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
		return ad;
	}


}
