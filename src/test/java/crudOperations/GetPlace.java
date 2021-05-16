package crudOperations;
import deserialization.get.GetPlaceResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Params;

public class GetPlace {
	
	public static void main(String [] args) {
		String anyPlaceID_thatIsntDeletedYet= "a8a390b84b598b14895b5e62aa5d0605";
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		RequestSpecification req= RestAssured
								  .given()
								  .contentType(ContentType.JSON)
								  .queryParam(Params.key,Params.keyValue)
								  .queryParam(Params.place_id,anyPlaceID_thatIsntDeletedYet);
		
		Response response = req.get("maps/api/place/get/json");
		response.then().log().all();
		
		GetPlaceResponse res= response.as(GetPlaceResponse.class);
		System.out.println(res.getLocation().getLatitude());
		System.out.println(res.getLocation().getLongitude());
	}
}
