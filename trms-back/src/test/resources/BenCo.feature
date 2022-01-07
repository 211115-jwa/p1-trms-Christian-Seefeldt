Feature: approving a request as a benefits cordinator

Scenario Outline: Approving a request correctly
	Given the user is on the approval page
	When the user clicks the approve button
	Then the page says request approved
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Not approving a request correctly
	Given the user is on the approval page
	When the user enters a comment
	And the user clicks the disapprove button
	Then the page says request denied
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Not approving a request incorrectly
	Given the user is on the approval page
	When the user clicks the disapprove button
	Then the page says no comment given
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Requesting adittional information
	Given the user is on the approval page
	When the user clicks the request info button
	Then the page says info request sent
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
		

Scenario Outline: Approving a request correctly with modified values
	Given the user is on the approval page
	When the user enters '<costs>'
	And the user clicks the approve button
	Then the page says request approved
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		

		