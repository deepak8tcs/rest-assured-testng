package testcases;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.is;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Way1_Misc_Old {

	//@Test
	public void GetPetDetails()
	{   

		//BDD style: Gherkin( Given When Then ) style

		Response response = 
				given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				when().
				get("https://petstore.swagger.io/v2/pet/1");

		response.then().statusCode(200);
		response.then().body("id", Matchers.any(Integer.class));
		response.then().body("id", Matchers.is(1));
		response.then().body("id", Matchers.not(9999));
		response.then().body("tags.id", Matchers.hasItems(0));
		response.then().body("name", Matchers.is("doggie-10")); //or
		response.then().body("name", is("doggie-10"));
		response.then().body("name", Matchers.is("doggie-10"));
		response.then().body("name", Matchers.containsString("doggie"));


		Assert.assertEquals(response.getStatusCode(), 200,"incorrect response code");
		//testNG assertion has benefit that we can give custom message in case of failure
		System.out.println("Response Body is =>  " + response.body().asString());

		//	    given().
		//	    when().
		//	        get("http://ergast.com/api/f1/2017/circuits.json").
		//	    then().
		//	        assertThat().
		//	        statusCode(200).
		//	    and().
		//	        contentType(ContentType.JSON).
		//	    and().
		//	        header("Content-Length",equalTo("4567"));

	}
	//@Test
	public void GetWeatherDetails()
	{   

		//Non-BDD style: 

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		httpRequest.header("Authorization","xyz");

		Response response = httpRequest.get("/Hyderabad");
		//Response response = httpRequest.request(Method.GET, "/Hyderabad"); //alternative syntax


		String responseBody = response.body().asString(); 
		//String responseBody = response.getBody().asString(); getBody and body gives same value
		System.out.println("Response Body is =>  " + responseBody);

		//verify response status code
		Assert.assertEquals(response.getStatusCode(), 200,"incorrect response code");
		Assert.assertEquals(response.getStatusLine() /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Incorrect status code returned");

		//verify response header
		Assert.assertEquals(response.header("Content-Type"), "application/json" );
		//Assert.assertEquals(response.getHeader("Content-Type"), "application/json" ); //gives same value as above
		Assert.assertEquals(response.header("Server") , "nginx" );
		Assert.assertEquals(response.header("Content-Encoding") , "gzip");

		Headers allHeaders = response.headers();
		//Headers allHeaders = response.getHeaders();  //gives same value as above ie collection of headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + ", Value: " + header.getValue());
		}

		//verify response body

		Assert.assertTrue(responseBody.toLowerCase().contains("hyderabad"));

		//JSONPath defines expressions to traverse through a JSON document to reach to a subset of the JSON.

		JsonPath jsonPathEvaluator = response.jsonPath();

		String city = jsonPathEvaluator.get("City");

		System.out.println("City received from Response " + city);

		Assert.assertEquals(city, "Hyderabad", "Correct city name not received in the Response");



	}

	@SuppressWarnings("unchecked")
	//@Test
	public void registerUser()
	{		

		//creating a json payload using simple josn library

		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();	

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "eddkv1"); // Cast
		requestParams.put("LastName", "edvarma1");
		requestParams.put("UserName", "eddkvsdimpleuser2dd20111");
		requestParams.put("Password", "edpassword11");

		requestParams.put("Email",  "eddkvsample2ee26d91@gmail.com");
		request.body(requestParams.toJSONString());

		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS", "Correct Success code was not returned");
	}

	//@Test
	public void testWithPathAndQueryParam() {

		//‘Path Parameter’or URI parameter is basically used to identify a specific resource or resources.
		//‘Query Parameter’ is used to filter or sort the resources. starts with ? and combined with & for multiple query params

		String id = "10";
		RestAssured.baseURI = "https://petstore.swagger.io/";
		Response response = null;

		response = RestAssured.given()
				.pathParam("id", id)
				//.pathParam("name", "john")
				.when()
				.get("v2/pet/{id}"); //we can give relative path here, no need of absolute url.

		System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());

		response = RestAssured.given()
				.when()
				.get("v2/pet/{id}/{name}",id,"john");  

		//		rest assured path param is just to improve the readability otherwise
		//		we can achieve the same thing using string conact + or replace() etc

		// QUERY Param:

		//to form a url like this=> https://petstore.swagger.io/v2/pet/10?page=2&status=active
		response = RestAssured.given()
				.pathParam("id",10)
				.queryParam("page", 2)
				.queryParam("status", "active")
				.when()
				.get("v2/pet/{id}"); //we can give relative path here, no need of absolute url.

	}

	//@Test
	public void testWithDifferentAuthentications() {

		//The basic authentication scheme requires the consumer to send user id and a password encoded in Base64.
		RestAssured.baseURI = "https://petstore.swagger.io/";
		Response response = 
				given()
				.auth()
				.basic("user1", "user1Pass")
				.when()
				.get("v2/pet/10");

		//Using Oauth2
		response = 
				given()
				.auth()
				.oauth2("accessToken")
				.when()
				.get("v2/pet/10");

		//Using Oauth1.0 (old oauth )
		response = 
				given()
				.auth()
				.oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
				.when()
				.get("v2/pet/10");

		//Preemptive Authentication
		response = 
				given()
				.auth()
				.preemptive()
				.basic("user1", "user1Pass")
				.when()
				.get("v2/pet/10");

		//Using Digest Authentication
		response = 
				given()
				.auth()
				.digest("userName", "password")
				.when()
				.get("v2/pet/10");

		//Using Form Authentication
		response = 
				given()
				.auth()
				.form("userName", "password")
				.when()
				.get("v2/pet/10");
	}

}
