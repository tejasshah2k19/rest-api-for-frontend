# rest-api-for-frontend
This application helps frontend developer to integrate and test REST api in any frontend - angular , react ,android etc. 


BASIC REST API

Entity - Table is Ingredient

Ingredient => Fields =>  {
  ingredientId int,
  name String,
  souce String,
  active boolean,
  description String,
  notConsumeBy String  
}

Main URL Of REST Doc :
https://healthy-me-rest-api.herokuapp.com/swagger-ui.html


List Ingredients :-   GET  https://healthy-me-rest-api.herokuapp.com/api/ingredients

Add Ingredient :- POST https://healthy-me-rest-api.herokuapp.com/api/ingredient Parameters : [ refer above Ingredient Table ] 

Get Single Ingredient :- GET  https://healthy-me-rest-api.herokuapp.com/api/ingredient/2  [ 2 is the unique id of ingredient that you want to retrive ] 

Update Ingredient  :-   PUT https://healthy-me-rest-api.herokuapp.com/api/ingredient Parameters : [ refere above Ingredient Table ] 

Delete Ingredient :-  DELETE  https://healthy-me-rest-api.herokuapp.com/api/ingredient/2    [ 2 is the unique id of ingredient which will remove ] 



For Browser - REST DOC : https://healthy-me-rest-api.herokuapp.com/swagger-ui.html


###  NOTES ### 
If you get invalid user or invalid access error then, You must need to add authToke in header for every request. 

How can i get authToken ? you need To login (use authentication api from doc ) , once you login into server you will get authToken to access the api. 

In authentication api you will get authToke , you need to set this token in every request then only you can access api. 

