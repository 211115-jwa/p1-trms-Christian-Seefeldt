Feature: submitting a reimbursement as a user

Scenario Outline: Submitting a reimbursment request correctly
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request submited
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Submitting a reimbursment request incorrectly
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request incorrect
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Submitting a reimbursment request incomplete
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request not complete
	
	Examples:
		|						|						|
		|						|						|
		|						|						|
		
Scenario Outline: Submitting a reimbursment with no avalible funds
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says funds exceeded
	
	Examples:
		|						|						|
		|						|						|
		|						|						|