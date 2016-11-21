# Java Spring MVC, Persistence, and Transactions

This is a mini web application to manage office phones and phone users through create, view, update, and delete. This application needs to be hosted on a Tomcat server. It uses Spring’s MVC framework for the UI implementation, and JPA for the persistence.


### Relationship Between Objects
* Each phone can be assigned to multiple users, and each user can have multiple phones. 
* Each phone (and user) has an address, which is an embedded object with four fields mapped to the corresponding four columns in the phone (or user) table.
* When a person is deleted, all phones assigned to him are automatically unassigned from him.
* A phone cannot be deleted if it is assigned to at least one user.

### Endpoints
#### (1) Get a user as HTML
```url
http://hostname/user/userId 
```

Method: GET

This returns an HTML that renders the given user ID’s user record. The user fields are part of an HTML form.
* Firstname, lastname, address, and title are editable. 
* List of assigned phones is shown. The phones are NOT editable here. 
* The HTML page should contains two buttons, Update and Delete. When Update is clicked, it updates the user as specified in (4), using HTTP POST. When the Delete button is created, it deletes the user using HTTP DELETE.
* If the user of the given user ID does not exist, a customized 404 HTML page with the message “Sorry, the requested user with ID XXX does not exist.” Note: XXX is the ID specified in the request. HTTP error code 404 is returned.

#### (2) Get a user back as JSON
```url
http://hostname/user/userId?json=true 
```
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

#### (3) Get the user creation HTML
```url
http://hostname/user 
```

Method: GET

This returns an HTML form that should be almost the same as (1), except that 
* All fields are initially empty and are editable except the ID.
* The page contains one button, labelled Create. When Create is clicked, it creates the user as specified in (4), using HTTP POST. 
* The return code follows the HTTP convention.

#### (4) Create or update a user
```url
http://hostname/user/userId?firstname=XX&lastname=YY&title=abc&street=AAA&city=BBB&state=CCC&zip=95012
```

Method: POST

This request creates or update the user for the given user ID. 
* For simplicity, all the user fields other than the ID (firstname, lastname, address, and title) are passed as query parameters, and it is assumed that the request always comes with all the fields specified. 
* The corresponding user is created/updated accordingly. 
* In the end, the request returns the newly created/updated user in HTML, the same as GET https://hostname/user/userId 

#### (5) Delete a user
```url
http://hostname/user/userId
```
Method: DELETE

This request deletes the user for the given user ID. 
* If the user does not exist, it returns the same 404 page as in (1) with error code 404.
* Otherwise, deletes the corresponding user, and redirects the request to the user creation page at https://hostname/user.  
* The phones are unassigned from the user.

#### (6) Get a Phone as HTML
```url
http://hostname/phone/phoneId 
```
Method: GET

This returns an HTML that renders the phone of the given ID. The phone fields are part of an HTML form.
* Phone number, description, and address are editable. 
* List of assigned users are shown. Widgets are provided to allow add/remove of users.
* The HTML page also contains two buttons, Update and Delete. When Update is clicked, it updates the phone as specified in (4), using HTTP POST. When the Delete button is created, it deletes the phone using HTTP DELETE.
* A phone cannot be deleted if there is still a user assigned to it: a 400 error is returned for such request. 
* If the phone of the given phone ID does not exist, a customized 404 HTML page with the message “Sorry, the requested phone with ID XXX does not exist.” Note: XXX is the ID specified in the request. HTTP error code 404 is returned.

#### (7) Get a phone back as JSON
```url
http://hostname/phone/phoneId?json=true
```
Method: GET

This returns the given phone’s record in JSON format.
```json
	{
		"id":"23",
		"number": "1234567890",
		"description": "home",
		"address": {
			"street": "San Salvador",
			"city": "San Jose",
			"state": "CA",
			"zip": "95012"
		},
		"users" : [
		{"id":"2", "firstname":"John", "lastname": "Oliver"},
		{"id":"21", "firstname":"John", "lastname": "Mayer"}
		]
	}
```
* This JSON is meant for readonly, and is not an HTML page or form. 
* The content matchs that in (6).  

#### (8) Get the phone creation HTML
```url
http://hostname/phone 
```

Method: GET

This returns an HTML form that is almost the same as (1), except that 
* All fields are initially empty and editable except the ID.
* The page contains one button, labelled Create. When Create is clicked, it creates the phone as specified in (4), using HTTP POST. 
* The return code follows the HTTP convention.

#### (9) Create or update a phone
```url
http://hostname/phone/phoneId?number=XX&description=YY&street=AAA&city=BBB&state=CCC&zip=95012&users[]=id1&users[]=id2
```
Method: POST

This request creates or update the phone for the given phone ID. 
* For simplicity, all the phone fields other than the ID (number and description) are passed as query parameters, and it is assumed that the request always comes with all the fields specified. 
* The users assignments is also taken care of. (Users are assigned to a phone using UserId, refer the URL above. Only existing users can be assigned to a phone.)
* The corresponding phone is created/updated accordingly. 
* In the end, the request returns the newly created/updated phone in HTML, the same as GET https://hostname/phone/phoneId 

#### (10) Delete a phone
```url
http://hostname/phone/phoneId
```
Method: DELETE

This request deletes the phone for the given phone ID. 
* If the phone does not exist, it returns the same 404 page as in (1) with error code 404.
* A phone cannot be deleted if it’s still assigned, or 400 errors will be returned.
* Otherwise, the corresponding phone is deleted, and redirected to the phone creation page at https://hostname/phone.  
* The phones are also unassigned from the user.
