Feature: logging in as a user

  Scenario Outline: logging in with correct credentials
    Given the user is on the login page
    And the user clicks the Log In Link
    When the user enters "<username>" and "<password>" to log in
    And the user clicks the submit button
    Then the navbar says "<username>"

    Examples: 
      | username | password |
      | BSmith   | p5       |
      | DFenton  | p4       |
      | SManoson | p3       |
      | JFenton  | p2       |
      | TFoley   | p1       |

  Scenario Outline: logging in with incorrect passwords
    Given the user is on the login page
    And the user clicks the Log In Link
    When the user enters "<username>" and "<password>" to log in
    And the user clicks the submit button
    Then the page says Incorrect Credentials

    Examples: 
      | username | password |
      | BSmith   | P5       |
      | BSmith   | p2       |
      | BSmith   |     1234 |

  Scenario Outline: logging in without filling fields
    Given the user is on the login page
    And the user clicks the Log In Link
    When the user enters "<username>" and "<password>" to log in
    And the user clicks the submit button
    Then the page says Incorrect Credentials

    Examples: 
      | username | password |
      |          | p5       |
      | BSmith   |          |
      |          |          |
