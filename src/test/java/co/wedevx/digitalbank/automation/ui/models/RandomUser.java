package co.wedevx.digitalbank.automation.ui.models;


import java.util.Random;
import java.util.UUID;


//A data model representing a user with all required fields.
//Fields: title, firstName, lastName, gender, dob, ssn, email, password, confirmPassword, address, locality, region, postalCode, country, homePhone, mobilePhone, workPhone.
//Includes a toString() method to print user data for debugging.
public class RandomUser {
    // Method to generate random email
    public String title;
    public String firstName;
    public String lastName;
    public String gender;
    public String dob;
    public String ssn;
    public String email;
    public String password;
    public String confirmPassword;
    public String address;
    public String locality;
    public String region;
    public String postalCode;
    public String country;
    public String homePhone;
    public String mobilePhone;
    public String workPhone;


    @Override
    public String toString() {
        return "RandomUser{" +
                "title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", address='" + address + '\'' +
                ", locality='" + locality + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';

    }

}
