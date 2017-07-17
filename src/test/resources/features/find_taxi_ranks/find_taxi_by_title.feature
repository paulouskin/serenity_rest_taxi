Feature: Find taxi stands by name
  In order to plan my getaway
  As a first time London bank robber
  I want to know all the taxi stands with a given part of name

  Scenario:Provide some info about taxi stands with similar names
    Given Joe is planning a getaway from Canary Wharf
    When he looks for the taxi rank with Cab name
    Then he should find the taxi ranks with the following details
      | commonName                  | NumberOfSpaces | OperationDays | OperationTimes |
      | Cab Road (Waterloo Station) | 10             | Mon - Sun     | 24 hours       |
      | 1 Cabot Square              | 4              | Mon - Sun     | 24 hours       |
      | 20 Cabot Square             | 5             | Mon - Sun     | 24 hours       |