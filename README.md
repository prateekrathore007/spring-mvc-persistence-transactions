#Java Spring MVC, Persistence, and Transactions

This is a mini web application to manage office phones and phone users through create, view, update, and delete. This application needs to be hosted on a Tomcat server. It uses Spring’s MVC framework for the UI implementation, and JPA for the persistence.


###Relationship Between Objects
* Each phone can be assigned to multiple users, and each user can have multiple phones. 
* Each phone (and user) has an address, which is an embedded object with four fields mapped to the corresponding four columns in the phone (or user) table.
* When a person is deleted, all phones assigned to him are automatically unassigned from him.
* A phone cannot be deleted if it is assigned to at least one user.

###Endpoints
####(1) Get a user as HTML
URL: https://hostname/user/userId 

Method: GET

This returns an HTML that renders the given user ID’s user record. The user fields are part of an HTML form.
* Firstname, lastname, address, and title are editable. 
* List of assigned phones is shown. The phones are NOT editable here. 
* The HTML page should contains two buttons, Update and Delete. When Update is clicked, it updates the user as specified in (4), using HTTP POST. When the Delete button is created, it deletes the user using HTTP DELETE.
* If the user of the given user ID does not exist, a customized 404 HTML page with the message “Sorry, the requested user with ID XXX does not exist.” Note: XXX is the ID specified in the request. HTTP error code 404 is returned.

####(2) Get a user back as JSON
URL: https://hostname/user/userId?json=true 

Method: GET

This returns the given user’s record in JSON format. 
```json
	{
		"id":"2",
		"firstname": "John",
		"lastname": "Oliver",
		"title": "Manager",
		"address": {
			"street": "1 Washington Square",
			"city": "San Jose",
			"state": "CA",
			"zip": "95012"
		},
		"phones" : [
		{"id":"100", "number":"3231214567", "description":"home"},
		{"id":"101", "number":"3231210000", "description":"office"}
		]
	}
```
* This JSON is meant for readonly, and is not an HTML page or form. 
* The content matchs that in (1).
