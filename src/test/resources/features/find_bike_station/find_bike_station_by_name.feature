Feature: Find bike station by name
  In order to find good bike to drive home
  As a man out of tube
  I want to search nearby bike stations

  Scenario: Find bike station and provide some information
    Given Jane is at London Bridge
    When she looks for the nearby bike station
    Then next bike points is not so far from her
    |commonName                     |latitude |longitude|
    |Snowsfields, London Bridge     |51.502153|-0.083632|
    |Duke Street Hill, London Bridge|51.506304|-0.087262|