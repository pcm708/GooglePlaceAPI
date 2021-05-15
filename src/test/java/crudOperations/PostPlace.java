package crudOperations;
import deserialization.post.PostPlaceResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Params;
import static utils.RequestBody.*;

public class PostPlace {
	
	public static void main(String [] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		Response response= RestAssured 
								  .given()
								  .log().all()
								  .contentType(ContentType.JSON)
								  .queryParam(Params.key,Params.keyValue)
								  .body(getBody())
								  .post("maps/api/place/add/json");
		
		PostPlaceResponse res= response.as(PostPlaceResponse.class);
		System.out.println(res.getStatus());
		System.out.println(res.getPlace_id());
		System.out.println(res.getScope());
		System.out.println(res.getReference());
	}
}
