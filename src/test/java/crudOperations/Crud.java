package crudOperations;
import static utils.RequestBody.getBody;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import deserialization.delete.DeletePlaceResponse;
import deserialization.get.GetPlaceResponse;
import deserialization.post.PostPlaceResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Params;

public class Crud {
	
	private RequestSpecification req;
	private static String place_id;
	private static String cityName;
	private Faker fk;
	
	@BeforeTest
	public void setup() {
		fk = new Faker();
		cityName = fk.address().cityName();
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		req = RestAssured
				  .given()
				  .log().all()
				  .contentType(ContentType.JSON)
				  .queryParam(Params.key,Params.keyValue);
	}
	
	@Test
	public void verifyPostResponseCall() {
		Response res= RestAssured
					 .given(req)
					 .body(getBody(cityName))
					 .post("maps/api/place/add/json");
		
		res.then().log().all();
		PostPlaceResponse postResponse = res.as(PostPlaceResponse.class);
		place_id = postResponse.getPlace_id();  //Fetching the place id
		Assert.assertEquals(res.statusCode(),200,"Assertion Failed:: Unable to add place with place_id"+place_id);
		System.out.println("\n###### Assertion Passed:: Place added successfully, place_id generated : "+place_id+ " ######\n");
	}
	
	@Test(dependsOnMethods = "verifyPostResponseCall")
	public void verifyGetResponseCall() {
		Response res = RestAssured
				.given(req)
				.queryParam("place_id",place_id)
				.get("/maps/api/place/get/json");
		res.then().log().all();
		
		GetPlaceResponse getresponse = res.as(GetPlaceResponse.class);
		Assert.assertEquals(cityName,getresponse.getName(),"Assertion Failed:: Unable to find place with place_id"+place_id);
		System.out.println("\n###### Assertion Passed:: Place found with place_id : "+place_id+" ######\n");
	}
	
	@Test(dependsOnMethods = "verifyGetResponseCall")
	public void verifyDeleteResponseCall() {
		Response res = RestAssured
				.given(req)
				.body("{\r\n"
						+ "    \"place_id\":\""+place_id+"\"\r\n"
						+ "}\r\n"
						+ "")
				.delete("/maps/api/place/delete/json");
		res.then().log().all();
		
		DeletePlaceResponse deleteresponse= res.as(DeletePlaceResponse.class);
		Assert.assertEquals("OK",deleteresponse.getStatus(),"Assertion Failed:: Unable to find place with place_id: "+place_id);
		System.out.println("\n###### Assertion Passed:: Place deleted having place_id : "+place_id+ " ######\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
