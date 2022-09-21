Feature: Test CustomerController

  Scenario: Retrieve from an empty customers list
    When I try to retrieve all the customers from an empty list
    Then I receive an empty list

#  Scenario: Save a customer, then delete it
#    When I try to save a customer named "Customer0" in the database
#    Then the status code received is "200 OK"
#    When I try to get the newly created customer
#    Then I receive a status equal to 200 and a customer with name "Customer0"
#    When I try to delete the newly created customer
#    Then the status code received is "200 OK"