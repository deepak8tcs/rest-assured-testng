package test.base;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import request.pojo.PetRequest;
import utils.JsonReader;
import utils.PropertyReader;

public class TestBase {

	public static PropertyReader properties =PropertyReader.getInstance();

	public String id;

	public String getId() {
				return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@BeforeSuite
	public void setUpRequest(){
		RestAssured.baseURI =properties.getPropertyValue("baseurl");
	}

	@AfterSuite
	public void stearDownRequest(){
		RestAssured.reset();
	}
	
	@BeforeTest
	public void setUpTestData(){
		setUpPetData();
	}


	public Response postRequest(String requestJson, String api) {	
		Reporter.log("POST Request URL is : "+RestAssured.baseURI+api, true);
		Reporter.log("POST Request Body is : "+requestJson,true);

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

	public void setUpPetData(){
		
		Reporter.log("Setting up test data.",true);
		PetRequest petRequest =JsonReader.getJavaObjectFromJsonFile("pet.json", PetRequest.class);
		Response response= postRequest(JsonReader.getJsonStringFromObject(petRequest),"v2/pet");
		Assert.assertEquals(response.getStatusCode(), 200);
		setId(response.jsonPath().getString("id"));
		Reporter.log("PetId is set to: "+getId(),true);
	}


}
