package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import request.pojo.PetRequest;
import test.base.TestBase;
import utils.JsonReader;

public class PetManagement extends TestBase {


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

		//
		//		JsonPath jp = response.jsonPath();
		//		Assert.assertEquals(jp.get("name"), "doggie-10");
		//		Assert.assertEquals(jp.get("tags[0].id").toString(), "0");
		//		Assert.assertEquals(jp.get("tags[-1].id").toString(), "1");
		//		Assert.assertEquals(jp.get("tags.findAll{tag -> tag.id >= 0}.size()").toString(), "2");
		//		Assert.assertFalse(jp.param("tagId", 0).get("tags.findAll{tag -> tag.id == tagId}").toString().isEmpty());

		//		System.out.println("Get pet name: "+jp.get("name")); 
		//		System.out.println("Get the first tag id:: "+jp.get("tags[0].id")); //Get the first tag id:
		//		System.out.println("Get the last tag id:: "+jp.get("tags[-1].id")); //Get the last tag id:
		//		System.out.println("List<String> ids: "+jp.get("tags.id"));  //List<String>
		//		System.out.println("dkv: "+jp.get("tags.findAll{tag -> tag.id > 0}"));
		//		System.out.println("list size satisfying condition: "+jp.get("tags.findAll{tag -> tag.id >= 0}.size()"));
		//		System.out.println("dkv: "+jp.param("tagId", 0).get("tags.findAll{tag -> tag.id == tagId}"));



	}


	@Test(priority =1)
	public void getPetDetails() throws Exception
	{		
		Response response= getRequest("v2/pet/"+getPetId());

		Assert.assertEquals(response.getStatusCode(), 200);

		PetRequest petResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), PetRequest.class);
		Assert.assertEquals(petResponse.getTags().get(0).getId().intValue(), 0);
		Assert.assertTrue(petResponse.getTags().size() > 0);
		Assert.assertEquals(petResponse.getTags().stream().filter(x -> x.getId() >=0).count(), 2);

	}

	@Test(priority =2)
	public void updatePetDetails() throws Exception
	{		

		PetRequest petRequest =JsonReader.getJavaObjectFromJsonFile("pet.json", PetRequest.class);
		setPetRequest(petRequest);	
		Response response= putRequest(JsonReader.getJsonStringFromObject(getPetRequest()),"v2/pet");

		Assert.assertEquals(response.getStatusCode(), 200);

		PetRequest petResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), PetRequest.class);

		Assert.assertEquals(petResponse.getName(), petRequest.getName());
		Assert.assertEquals(petResponse.getId(), petRequest.getId());
		Assert.assertEquals(petResponse.getStatus(), petRequest.getStatus());		
		Assert.assertTrue(petResponse.getTags().size() > 0);
		Assert.assertEquals(petResponse.getTags().stream().filter(x -> x.getId() >=0).count(), petResponse.getTags().size());

	}

	@Test(priority = 3)
	public void deletePetDetails() throws Exception
	{		
		Response response= deleteRequest("v2/pet/"+getPetId());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.getBody().asString().isEmpty());

	}


}





