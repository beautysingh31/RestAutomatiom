package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAutomation.RestAutomation.Payloads;
import io.restassured.path.json.JsonPath;

public class CourseTitles 
{

	@Test
	public static void courseDetails()
	{
		//print number of courses
		JsonPath js=new JsonPath(Payloads.CourseTitles());
	
		int count=js.getInt("courses.size()");
		
		
		System.out.println(count);
		
		//Print the prucahse amount
		int purchsamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchsamount);
		
		
		//Print ttile of first course
		String FrstTitle=js.get("courses[0].title");
		System.out.println(FrstTitle);
		
		//Print all coursetitles
		
		for(int i=0;i<count;i++)
		{
			String alltitles=js.get("courses["+i+"].title");
			System.out.println(alltitles);
			int allprices=js.getInt("courses["+i+"].price");
			System.out.println(allprices);
		}
		
		//get No of copies sold by RPA
		
		for(int j=0;j<count;j++)
		{
			String alltitles=js.get("courses["+j+"].title");
			if(alltitles.equalsIgnoreCase("RPA"))
			{
			System.out.println(js.get("courses["+j+"].copies"));	
			break;
			
			}
			}
		//Comapre the pircehase amount with all the copies sold*price
		int sum=0;
		
	for (int k=0;k<count;k++)
	{
		int allprices=js.getInt("courses["+k+"].price");
		int alcopies=js.get("courses["+k+"].copies");
		
		int amount=allprices*alcopies;
		System.out.println("total amount is "+amount);
		sum=sum+amount;
		
			
	}
	System.out.println("Actual total amount is "+sum);	
	int expSum=js.get("dashboard.purchaseAmount");
	Assert.assertEquals(sum, expSum);	
	System.out.println();
		
	}
}
