package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import request.pojo.PetRequest;
import test.base.TestBase;
import utils.JsonReader;

public class RegisterPet extends TestBase {


	private PetRequest petRequest;

	private String petId;

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public PetRequest getPetRequest() {
		return petRequest;
	}

	public void setPetRequest(PetRequest petRequest) {
		this.petRequest = petRequest;
	}


	@Test(priority =0)
	public void petRegistration() throws Exception
	{		

		PetRequest petRequest =JsonReader.getJavaObjectFromJsonFile("pet.json", PetRequest.class);
		setPetRequest(petRequest);	
		Response response= postRequest(JsonReader.getJsonStringFromObject(getPetRequest()),"v2/pet");

		Assert.assertEquals(response.getStatusCode(), 200);
		setPetId(response.jsonPath().getString("id"));

		PetRequest petResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), PetRequest.class);

		Assert.assertEquals(petResponse.getName(), petRequest.getName());
		Assert.assertEquals(petResponse.getId(), petRequest.getId());
		Assert.assertEquals(petResponse.getStatus(), petRequest.getStatus());		
		Assert.assertTrue(petResponse.getTags().size() > 0);
		Assert.assertEquals(petResponse.getTags().stream().filter(x -> x.getId() >=0).count(), petResponse.getTags().size());

	}


}





