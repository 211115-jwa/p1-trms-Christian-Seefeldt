Feature: viewing requests as a user

  Background: Given the user is on the view requests page

  Scenario: Viewing requests as employee
    Given the user is on the view requests page
    And the user is logged in
    When the user inputs "<empId>" to search by employee id
    And clicks the search button
    Then the table has requests in it
