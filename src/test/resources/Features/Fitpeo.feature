@test
Feature: FitPeo Revenue Calculator

  Scenario: Validate the Total Recurring Reimbrushment using Fitpeo Revenue Calculator
    Given Launch the web browser and navigate to FitPeo Homepage
    When I navigate to the Revenue Calculator Page
    And I scroll down to the slider section
    And I adjust the slider to set its value to 816
    Then the bottom text field value should be updated to "820"
    When I update the bottom text field to "560"
    Then the slider position should be updated to the text field value "560"
    When I scroll down and select the checkboxes for all CPT codes "99091,99453,99454,99474"
    Then the Total Recurring Reimbursement should show the value Dollars: "$75600"

