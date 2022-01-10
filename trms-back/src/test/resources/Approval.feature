Feature: Approving a request as a supervisor/dept head
  
  Backgound: Given the user is on the Approval page

  Scenario: the requests by requestor id show up in the pending requets table
    Given the user is on the approval page
    And the user is logged in as an approver
    When the user inputs "<empId>" to search by requestor id
    And clicks the view all requests button
    Then the table has pending requests in it
