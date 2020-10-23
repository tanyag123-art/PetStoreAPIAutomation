package stepDefinitions;

import io.cucumber.java.en.*;
import pageClass.PetStorePage;

public class petStoreStepDefinitions {


	PetStorePage petStorePage = new PetStorePage();

	@Given("user prepare {string} API to get pet status")
	public void user_calls_using_http_method(String baseURL) {
		petStorePage.preparetheGetStatusrequest(baseURL);

	}

	@When("user hit the getPetStatusAPI to get the pet with {string} status")
	public void the_api_should_return_in_case_of_success(String petStatus) {
		petStorePage.hitRequestforSuccess(petStatus);
	}

	@Then("the response should contain all the pets with status {string}")
	public void the_response_should_contain_all_the_pet_pets_with_status(String petStatus) {
		petStorePage.verifyTheResponse(petStatus);
	}
	
	@Given("user build request body and prepare {string} API for {int}, {string}, {string}, {string} and {string}")
	public void user_build_request_body_and_prepare_api_for_and(String baseURI, int id, String name, String category, String tag, String status) {
	    petStorePage.buildRequestBody(id, name, category, tag, status);
	    petStorePage.preparethePOSTendPoint(baseURI);
	}

	@When("user hit the API to add the pet succesfully")
	public void user_hit_the_api_to_add_the_pet_succesfully() {
	    petStorePage.hitAddPetAPI();
	}

	@Then("the pet should be successfully added with status as {int}")
	public void the_pet_should_be_successfully_added_with_status_as(Integer statusCode) {
	   petStorePage.verifyTheStatusCode(statusCode);
	}

	@Then("the API the API response should contain all the details of the pet as {int}, {string}, {string}, {string} and {string}")
	public void the_api_the_api_response_should_contain_all_the_details_of_the_pet_as_and(int id, String name, String category, String tag, String status) {
	   petStorePage.verifyTheResponseBody(id, name, category, tag, status);
	}

	@When("user hit the API to update the pet status succesfully")
	public void user_hit_the_api_to_update_the_pet_status_succesfully() {
		 petStorePage.hitUpdatePetAPI();
	}

	@Then("the pet should be successfully updated with status as {int}")
	public void the_pet_should_be_successfully_updated_with_status_as(Integer statusCode) {
		 petStorePage.verifyTheStatusCode(statusCode);
	}

	@Then("the API response should contain the status of the pet as {string}")
	public void the_api_response_should_contain_the_status_of_the_pet_as(String status) {
		 petStorePage.verifyTheStatusInResponseBody(status);
	}
	
	@Given("user prepare {string} API to delete an existing pet")
	public void user_prepare_api_to_delete_an_existing_pet(String baseURL) {
		petStorePage.preparetheGetStatusrequest(baseURL);
	}

	@When("user hit the delete API to for the pet with id {int}")
	public void user_hit_the_delete_api_to_for_the_pet_with_id(Integer id) {
	   petStorePage.hitDeletePetAPI(id);
	}

	@Then("the pet should be successfully deleted with status as {int}")
	public void the_pet_should_be_successfully_deleted_with_status_as(Integer statusCode) {
		petStorePage.verifyTheStatusCode(statusCode);
	}

	@Then("the API response should contain the message as {string} and code as {int}")
	public void the_api_response_should_contain_the_message_as_and_code_as(String message, int statusCode) {
		 petStorePage.verifyTheMessageInResponseBody(message, statusCode);
	}
}
