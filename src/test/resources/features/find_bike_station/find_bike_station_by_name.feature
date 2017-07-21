Feature: Find bike station by name
  In order to find good bike to drive home
  As a man out of tube
  I want to search nearby bike stations

  Scenario: Find bike station and provide some information
    Given Jane come out from Canary Wharf station
    When she looks for the nearby bike station
    Then she should find next bike stations
    |commonName                     |
    |South Quay East, Canary Wharf  |
    |Montgomery Square, Canary Wharf|
    |Heron Quays DLR, Canary Wharf  |