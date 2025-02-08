Feature: Digital Bank Registration Page
  Background:
    Given The following user with "usersteve@gmail.com" is not in DB
    Given user navigates to Digital Bank signup page

@Test
  Scenario: Positive Case. As a user i want to successfully create DigitalBank account
    When user creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn          | email               | password      | confirmPassword | address         | locality   | region   | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Ms.   | Steve     | Jobs     | F      | 01/01/1992 | 123-44-22555 | usersteve@gmail.com | ThisIsTester1 | ThisIsTester1   | 123 Town Square | Dubai city | Emirates | 70770      | UAE     | 5167772345 | 5136789090  | 5136789090 | true           |
  Then user should be displayed with the message "Success Registration Successful. Please Login"
    Then the following user info should be saved in the digital bank
      | title | firstName | lastName | gender | dob        | ssn          | email               | password      | confirmPassword | address         | locality   | region   | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Ms.   | Steve     | Jobs     | F      | 01/01/1992 | 123-44-22555 | usersteve@gmail.com | ThisIsTester1 | ThisIsTester1   | 123 Town Square | Dubai city | Emirates | 70770      | UAE     | 5167772345 | 5136789090  | 5136789090 | true              | true             | true                  | true    |

  @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank i want to make sue users can not register without providing all valid data
    When user creates account with following fields

      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | confirmPassword   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <confirmPassword> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |

    Then user should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:

      | title | firstName | lastName | gender | dob        | ssn | email | password    | confirmPassword | address     | locality   | region     | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark | fieldWithError  | errorMessage                        |
      |       |           |          |        |            |     |       |             |                 |             |            |            |            |         |            |             |            |                | title           | Please select an item in the list.  |
      | Ms.   |           |          |        |            |     |       |             |                 |             |            |            |            |         |            |             |            |                | firstName       | Please fill out this field.         |
      | Ms.   | Jack      |          |        |            |     |       |             |                 |             |            |            |            |         |            |             |            |                | lastName        | Please fill out this field.         |
      | Ms.   | Jack      | Test     |        |            |     |       |             |                 |             |            |            |            |         |            |             |            |                | gender          | Please select one of these options. |
      | Ms.   | Jack      | Test     | M      |            |     |       |             |                 |             |            |            |            |         |            |             |            |                | dob             | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       |             |                 |             |            |            |            |         |            |             |            |                | ssn             | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       |             |                 |             |            |            |            |         |            |             |            |                | email           | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       |             |                 |             |            |            |            |         |            |             |            |                | password        | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       |             |                 |             |            |            |            |         |            |             |            |                | confirmPassword | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     |             |            |            |            |         |            |             |            |                | address         |                                     |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 |            |            |            |         |            |             |            |                | locality        |                                     |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California |            |            |         |            | 0507489099  |            |                | region          |                                     |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California | LosAngeles |            |         |            |             |            |                | postalCode      | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California | LosAngeles | 00004      |         |            |             |            |                | country         |                                     |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California | LosAngeles | 00004      | US      |            |             | 0507489099 |                | homePhone       | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California | LosAngeles | 00004      | US      | 0507489099 |             |            |                | mobilePhone     | Please fill out this field.         |
      | Ms.   | Jack      | Test     | M      | 01/01/1992 |     |       | NewPass123! | NewPass123!     | Main St.123 | California | LosAngeles | 00004      | US      |            | 0507489099  |            |                | workPhone       | Please fill out this field.         |

