package test.base;

import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

public class TestBase {
	
	public static PropertyReader properties =PropertyReader.getInstance();
		
	@BeforeSuite
	public void setUpRequest(){
		RestAssured.baseURI =properties.getPropertyValue("baseurl2");

	}
	
	
	
	public Response postRequest(String requestJson, String api) {	
		Reporter.log("POST Request URL is : "+RestAssured.baseURI+api, true);
		Reporter.log("POST Request Body is : "+requestJson,true);
//		
//		RequestSpecification request = RestAssured.given();
//		request.body(requestJson);
//		Response response = request.post(api);
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(requestJson)
				.when()
				.post(api);
		
		Reporter.log("POST Response Code is : "+response.getStatusCode(),true);
		Reporter.log("POST Response Body is : "+response.getBody().asString(),true);
		
		return response;
		
	}
	
	public Response putRequest(String requestJson, String api) {	
		Reporter.log("PUT Request URL is : "+RestAssured.baseURI+api, true);
		Reporter.log("PUT Request Body is : "+requestJson,true);
//		
//		RequestSpecification request = RestAssured.given();
//		request.body(requestJson);
//		Response response = request.post(api);
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(requestJson)
				.when()
				.put(api);
		
		Reporter.log("PUT Response Code is : "+response.getStatusCode(),true);
		Reporter.log("PUT Response Body is : "+response.getBody().asString(),true);
		
		return response;
		
	}
	
	public Response getRequest(String api) {	
		Reporter.log("GET Request URL is : "+RestAssured.baseURI+api, true);
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get(api);
		
		Reporter.log("GET Response Code is : "+response.getStatusCode(),true);
		Reporter.log("GET Response Body is : "+response.getBody().asString(),true);
		
		return response;
		
	}
	
	public Response deleteRequest(String api) {	
		Reporter.log("DELETE Request URL is : "+RestAssured.baseURI+api, true);
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.delete(api);
		
		Reporter.log("DELETE Response Code is : "+response.getStatusCode(),true);		
		return response;
		
	}

}
