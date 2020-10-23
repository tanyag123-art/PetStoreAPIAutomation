Feature: Pet store API validation

@PET_01
Scenario Outline: Verify if all available pets are returned by getPetByStatusAPI
	Given user prepare "<GetPetByStatusAPI>" API to get pet status
	When user hit the getPetStatusAPI to get the pet with "<petStatus>" status 
	Then the response should contain all the pets with status "<petStatus>"
	
	Examples:
	|GetPetByStatusAPI									|	method 	|	statusCode	| petStatus	|
	|https://petstore.swagger.io/v2/pet/findByStatus	| GET		|	200			| available	|
	
	
	
	@PET_02
Scenario Outline: Verify if a pet with status available is added to the store
	Given user build request body and prepare "<AddPetAPI>" API for <id>, "<name>", "<category>", "<tag>" and "<status>"
	When user hit the API to add the pet succesfully
	Then the pet should be successfully added with status as <statusCode>
	Then the API the API response should contain all the details of the pet as <id>, "<name>", "<category>", "<tag>" and "<status>"
	
	Examples:
	|AddPetAPI							|	method 	|	statusCode	| id			| status		| name		|category	| tag  |
	|https://petstore.swagger.io/v2/pet	| POST		|	200			|	23544		| available		|Buzo		|Dogs		|BS01	|
	

	Scenario Outline: Verify if a pet with status is updated as sold
	Given user build request body and prepare "<AddPetAPI>" API for <id>, "<name>", "<category>", "<tag>" and "<status>"
	When user hit the API to update the pet status succesfully
	Then the pet should be successfully updated with status as <statusCode>
	Then the API response should contain the status of the pet as "<status>"
	
	Examples:
	|AddPetAPI							|	method 	|	statusCode	| id			| status		| name		|category	| tag  |
	|https://petstore.swagger.io/v2/pet	| PUT		|	200			|	23544		| sold			|Buzo		|Dogs		|BS01	|
	

Scenario Outline: Verify if delete pet API succesfully deletes existing pet
	Given user prepare "<DeletePetAPI>" API to delete an existing pet
	When user hit the delete API to for the pet with id <id>  
	Then the pet should be successfully deleted with status as <statusCode>
	Then the API response should contain the message as "<message>" and code as <statusCode>
	
	Examples:
	|DeletePetAPI									|	method  		|	statusCode	| message	| id		|
	|https://petstore.swagger.io/v2/pet				| DELETE			|	200			| 23544		|	23544	|
	
	