package pageClass;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClass.AddPetReq;
import pojoClass.Category;
import pojoClass.Tags;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class PetStorePage {

	RequestSpecification requestSpecification ;
	Response response;
	AddPetReq petRequest;

	public void preparetheGetStatusrequest(String baseURL) {
		requestSpecification =new RequestSpecBuilder().setBaseUri(baseURL).build();
	}

	public void hitRequestforSuccess(String petStatus) {
		//		responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCode).build();
		response = given().log().all().spec(requestSpecification).
				queryParam("status", petStatus).
				when().get().
				then().log().all().statusCode(200).extract().response();
	}

	public void verifyTheResponse(String petStatus) {
//		String stringResponse = response.asString();
		Boolean flag = false;
		JsonPath json = response.jsonPath();
		List<String> statusofPets = json.get("status");
		System.out.println(statusofPets);
		for (String a : statusofPets) {
			if(a.equals(petStatus)){
				flag = true;
			}else {
				flag =false;
				break;
			}
		}
		Assert.assertTrue("API is not returning pet with available status", flag.equals(true));
	}

	public AddPetReq buildRequestBody(int id, String name, String category, String tag, String status) {
		petRequest = new AddPetReq();
		petRequest.setId(id);
		petRequest.setName(name);
		Category categoryDetail = new Category();
		categoryDetail.setId(0);
		categoryDetail.setName(category);
		petRequest.setCategory(categoryDetail);
		
		Tags tags = new Tags();
		tags.setId(0);
		tags.setName(tag);
		List<Tags> listTags = new ArrayList<Tags>();
		listTags.add(tags);
		petRequest.setTags(listTags);
		petRequest.setStatus(status);
		
		return petRequest;
	
	}

	public void preparethePOSTendPoint(String baseURI) {
		requestSpecification =new RequestSpecBuilder().setBaseUri(baseURI).setContentType(ContentType.JSON).build();
		
	}

	public void hitAddPetAPI() {
		response = given().log().all().spec(requestSpecification).body(petRequest).
				when().post().
				then().log().all().extract().response();
	}

	public void verifyTheStatusCode(int statusCode) {
		Assert.assertEquals("New pet is not added successfully", statusCode, response.getStatusCode());
	}

	public void verifyTheResponseBody(int id, String name, String category, String tag, String status) {
//		AddPetReq addpetres = response.as(AddPetReq.class);
		JsonPath js = response.jsonPath();
		Assert.assertEquals("New pet is not added successfully", id, js.getInt("id"));
		Assert.assertEquals("New pet is not added successfully", name, js.getString("name"));
		Assert.assertEquals("New pet is not added successfully", category, js.getString("category.name"));
		Assert.assertEquals("New pet is not added successfully", status, js.getString("status"));
//		Assert.assertEquals("New pet is not added successfully", status, addpetres.getId());
		
		
	}

	public void hitUpdatePetAPI() {
		response = given().log().all().spec(requestSpecification).body(petRequest).
				when().put().
				then().log().all().extract().response();
		
	}

	public void verifyTheStatusInResponseBody(String status) {
		JsonPath jsonRes = response.jsonPath();
		Assert.assertEquals("Status of the pet is not updated successfully", status, jsonRes.getString("status"));
		
	}

	public void hitDeletePetAPI(Integer id) {
		response = given().log().all().spec(requestSpecification).
				when().delete("/{id}", id).
				then().log().all().extract().response();
	}

	public void verifyTheMessageInResponseBody(String message, int statusCode) {
		JsonPath jsonRes = response.jsonPath();
		Assert.assertEquals("Pet is not updated successfully", message, jsonRes.getString("message"));
		Assert.assertEquals("Pet is not updated successfully", statusCode, jsonRes.getInt("code"));
		
	}		
	
}
