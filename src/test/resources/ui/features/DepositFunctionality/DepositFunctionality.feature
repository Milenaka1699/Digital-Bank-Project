#Create a new checking account for the user with a valid amount
    #Navigate to the "Deposit" page
    #Select the new account to process the deposit (save the current value of the account)
    #Input the amount to deposit
    #Click on the "Submit" button
    #Validate the new amount on the "View checking" page
    #Validate the operation is saved in the history table with the appropriate message:

#Navigate to the "Deposit" page
#Leave unselected the account field
#Input the amount to deposit
#Click on the "Submit" button
#Validate the error is displayed

  #Create a new checking account for the user with a valid amount
  #Navigate to the "Deposit" page
  #Select the new account to process the deposit (save the current value of the account)
  #Leave amount empty
  #Click on the "Submit" button
  #Validate that the error message is displayed

  #Create a new checking account for the user with a valid amount
  #Navigate to the "Deposit" page
    #Select the new account to process the deposit (save the current value of the account)
    #Provide negative amount
  #Click on the "Submit" button
  #Validate that the error message is displayed

  #Second:
  #Register a new user
  #Create a new checking account for the user with a valid amount
  #Navigate to the "Deposit" page
  #Select the new account to process the deposit (save the current value of the account)
  #Provide alphabetic characters to the amount field
  #Click on the "Submit" button
  #Validate that the error message is displayed

  #Third:
  #Register a new user
  #Create a new checking account for the user with a valid amount
  #Navigate to the "Deposit" page
  #Select the new account to process the deposit (save the current value of the account)
  #Provide numeric values mixed with special characters (ex: 10,99 or 10!)
  #Click on the "Submit" button
  #Validate that the error message is displayed



    Feature: Checking account - making a deposit

      Background:
        Given the user navigates to Digital Bank signup page
        Given user logins with email "steveandrew@gmail.com" and password "NewPassword456!"
        When the user creates a new checking account with following info:
          | checkingAccountType | accountOwnership | accountName           | initialDepositAmount |
          | Standard Checking   | Individual       | First Checking Milena | 100000.0             |
        Then user should get the green "Successfully created new Standard Checking account named First Checking Milena" message
        Then user navigates to Deposit Page

  # Positive Test Cases
     Scenario: Positive test case  to perform Deposit
        Given user  clicks on accounts Dropdown and selects "First Checking Milena" account
        Then the user makes a deposit of "1000.00" and clicks on submit button
        And the user should see the following transaction
          | date             | category | description               | depositAmount | balance  |
          | 2024-11-07 11:38 | Income   | 845335607 (DPT) - Deposit | 100000.0      | 100000.0 |


  # Negative Test Cases
      Scenario Outline: Negative test case. Checking deposit with invalid inputs
       Given user selects "<accountType>" and and inputs deposit amount "<depositAmount>"
        Then the "<fieldWithError>" field displays a "<errorMessage>" error message

        Examples:
          | accountType           | depositAmount | fieldWithError | errorMessage                       |
          |                       | 500.00        | accountType    | Please select an item in the list. |
          | First Checking Milena |               | depositTextBox | Please fill out this field.        |
          | First Checking Milena | -300.50       | depositTextBox | Please match the requested format. |
          | First Checking Milena | 100,50        | depositTextBox | Please match the requested format. |
          | First Checking Milena | thirty        | depositTextBox | Please match the requested format. |

