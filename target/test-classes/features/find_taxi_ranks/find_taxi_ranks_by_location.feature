Feature: Find taxi stands by location
  In order to plan my getaway
  As a car-less bank robber
  I want to know all the taxi stands in a given location

  Scenario: Provide useful practical information about each taxi rank
    Given Joe is planning a getaway from Tower Hill Underground Station
    When he looks for the closest taxi rank
    Then he should find the taxi ranks with the following details
      | commonName                          | NumberOfSpaces | OperationDays | OperationTimes |
      | Trinity Square (Tower Hill Station) | 2              | Mon - Sun     | 24 hours       |
