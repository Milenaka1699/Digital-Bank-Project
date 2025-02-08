package co.wedevx.digitalbank.automation.ui.utils;

import co.wedevx.digitalbank.automation.ui.models.RandomUser;
import com.github.javafaker.Faker;
import org.bouncycastle.asn1.cmp.Challenge;

import java.util.Stack;

//Contains the generateRandomUser() method.
//Uses the Faker library to generate realistic data for all fields in the RandomUser class.
public class UserGenerator {
    private static Faker faker = new Faker();

    public static RandomUser generateRandomUser() {
        RandomUser user = new RandomUser();
        user.title = faker.name().title();
        user.firstName = faker.name().firstName();
        user.lastName = faker.name().lastName();
        user.gender = faker.random().nextBoolean() ? "M" : "F";
        user.dob = faker.date().birthday().toString();
        user.ssn = faker.idNumber().ssnValid();
        user.email = faker.internet().emailAddress();
        user.password = faker.internet().password();
        user.confirmPassword = user.password;
        user.address = faker.address().streetAddress();
        user.locality = faker.address().city();
        user.region = faker.address().stateAbbr();
        user.postalCode = faker.address().zipCode();
        user.country = faker.address().country();
        user.homePhone = faker.phoneNumber().phoneNumber();
        user.mobilePhone = faker.phoneNumber().cellPhone();
        user.workPhone = faker.phoneNumber().phoneNumber();
        return user;

    }
}
