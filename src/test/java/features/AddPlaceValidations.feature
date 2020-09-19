Feature:	PlaceValiadtions

@Addplace@Regression
Scenario Outline:	Places to get added successfully
     Given Place payload and Query Param "<name>"  "<language>"  "<address>"
     When "AddPlaceApi" is called with "POST" http request
     Then successful "status" in body is "OK"
     And "scope" of the body is "APP"
     And verify that Placeid created maps to "<name>"  using "GetPlaceApi"
     
     
     Examples:
     
     |name|  |language| |address|
     |beauty||english|  |123 street nm road| 
   #  |mohit||spanish||2345 street|
 
 
 @Deleteplace@Regression 
   Scenario:	Places to be deleted successfully
       Given place payload with placeid
       When "DeletePlaceApi" is called with "DELETE" http request
       Then successful "status" in body is "OK"
       
       
   