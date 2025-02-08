#xercise Title: DBank - Building Test Framework

#Objective: In this set of exercises, your task is to build a test framework for a banking application called DBank. Use gained knowledge from previous classes and implement the automation tests with top-notch qualities.

#Task:
#The goal is to write positive test cases for deposit functionality.

#Register a new user

#Create a new savings account for the user with a valid amount

#Navigate to the "Deposit" page
#
#
#
#Select the new account to process the deposit (save the current value of the account)
#
#
#
#Input the amount to deposit
#
#
#
#Validate the new amount on the "View Savings" page
#
#
#
#Validate the operation is saved in the history table with the appropriate message:
#
#
#
#
#
#
#
#
#
#Operation type
#
#
#
#Category
#
#
#
#Description
#
#
#
#
#
#Deposit
#
#
#
#Income
#
#
#
#(DPT) Online Deposit
#
#
#
#
#
#Withdrawal
#
#
#
#Misc
#
#
#
#(WTH) Online Withdrawal

#Fees & Charges

#(OVF) Overdraft Fee for transaction ${transactionNumber}
#
#As well as validate the Date, Amount, and Balance columns.

#Be mindful of web page content and structure when implementing this exercise, as the button IDs and webpage URLs may vary based on the website you choose to work with.
#
#
#
#DBank is a demo project - meaning it has flaws and bugs. Don't be frustrated if tests don't pass. Check precisely your test case and once sure it is valid, report a bug to your group channel only.
#
#
#
#Avoid using hard sleep like Thread.sleep() to build more dynamic tests.
#
#
#
#Once done, click "submit" to complete the exercise.

  #19.2.2 Deposit - positive case for Savings Reset button *
  #
  #easy
  #
  #30 min
  #
  #Exercise Title: DBank - Building Test Framework
  #
  #
  #
  #Objective: In this set of exercises, your task is to build a test framework for a banking application called DBank. Use gained knowledge from previous classes and implement the automation tests with top-notch qualities.
  #
  #
  #
  #Task:
  #
  #The goal is to write positive test cases for deposit functionality.
  #Register a new user
  #Create a new savings account for the user with a valid amount
  #Navigate to the "Deposit" page
  #Select the new account to process the deposit (save the current value of the account)
  #Input the amount to deposit
  #Click on the "Reset" button
  #Validate all fields are reset to default

  #Note:
  #Be mindful of web page content and structure when implementing this exercise, as the button IDs and webpage URLs may vary based on the website you choose to work with.
  #DBank is a demo project - meaning it has flaws and bugs. Don't be frustrated if tests don't pass. Check precisely your test case and once sure it is valid, report a bug to your group channel only.
  #Avoid using hard sleep like Thread.sleep() to build more dynamic tests.
  #Once done, click "submit" to complete the exercise.
#Scenario Outline: Validate reset button functionality
#When a user opens a new account as follows:
#| bankingType   | accountType   | ownershipType | accountName   | initialDepositAmount |
#| <bankingType> | <accountType> | Individual    | First Account | 5000.00              |
#Then a green "Confirmation" success message is displayed "Successfully created new <accountType> account named First Account"
#When the user navigates to the "Deposit" page
#And the user inputs a deposit amount of "<depositAmount>"
#And clicks the "Reset" button
#Then all fields are reset to their default values

#Examples:
#| bankingType | accountType       | depositAmount |
#| Savings     | Money Market      | 200.00        |
#| Checking    | Standard Checking | 500.00        |
