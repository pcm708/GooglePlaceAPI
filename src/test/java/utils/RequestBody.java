package utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.javafaker.Faker;

import serialization.post.Location;
import serialization.post.PlaceBody;

public class RequestBody {
	//describing body
	public static PlaceBody getBody() {
		Faker fk =new Faker();
		
		Location location = new Location();
		location.setLat(fk.number().randomDouble(6, 1, 100));
		location.setLng(fk.number().randomDouble(6, 1, 100));
		
		PlaceBody body= new PlaceBody();
		body.setLocation(location);
		body.setAccuracy(fk.number().numberBetween(1, 100));
		body.setName(fk.address().cityName());
		body.setPhone_number(fk.phoneNumber().phoneNumber());
		body.setAddress(fk.address().fullAddress());
		body.setTypes(new ArrayList<String>(Arrays.asList("park","test")));
		body.setWebsite(fk.internet().domainName());
		body.setLanguage(fk.country().capital());
		return body;
	}
	
	public static PlaceBody getBody(String name) {
		Faker fk =new Faker();
		
		Location location = new Location();
		location.setLat(fk.number().randomDouble(6, 1, 100));
		location.setLng(fk.number().randomDouble(6, 1, 100));
		
		PlaceBody body= new PlaceBody();
		body.setLocation(location);
		body.setAccuracy(fk.number().numberBetween(1, 100));
		body.setName(name);
		body.setPhone_number(fk.phoneNumber().phoneNumber());
		body.setAddress(fk.address().fullAddress());
		body.setTypes(new ArrayList<String>(Arrays.asList("park","test")));
		body.setWebsite(fk.internet().domainName());
		body.setLanguage(fk.country().capital());
		return body;
	}
	
	
	
}
