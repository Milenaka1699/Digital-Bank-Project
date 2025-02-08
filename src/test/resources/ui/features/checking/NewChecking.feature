Feature:  Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "steveandrew@gmail.com" and password "NewPassword456!"
    When the user creates a new checking account with following data:
      | checkingAccountType | accountOwnership | accountName            | initialDepositAmount |
      | Standard Checking   | Individual       | Steve Checking Account | 100000.0             |
    Then user should see the green "Successfully created new Standard Checking account named Steve Checking Account" message
    And the user should see newly added account card
      | accountName            | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Steve Checking Account | Standard Checking | Individual | 486140266     | 0.0          | 100000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount   | balance  |
      | 2024-11-07 11:38 | Income   | 845335607 (DPT) - Deposit | 100000.0 | 100000.0 |
