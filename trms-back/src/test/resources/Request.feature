Feature: submitting a reimbursement as a user

Scenario Outline: Submitting a reimbursment request correctly
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request submited
	
	Examples:
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		|			12-12-2012			|				12:12		|				locations		|				description		|				50		|			Letter Grade			|			event_type			|
		|			11-11-2011			|				11:11		|				locations		|				description		|				45.50		|			Pass/Fail			|			event_type			|
		
Scenario Outline: Submitting a reimbursment request incorrectly
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request incorrect
	
	Examples:
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		
Scenario Outline: Submitting a reimbursment request incomplete
	Given the user is logged in and on the request page
	When the user enters '<event_date>', '<event_time>', '<locations>', '<description>', '<costs>', '<grading_format>', '<event_type>'
	And the user clicks the submit button
	Then the pages says request not complete
	
	Examples:
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		|			event_date			|				event_time		|				locations		|				description		|				costs		|			grading_format			|			event_type			|
		