package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {
    private FakeValuesService fakeValueServise = new FakeValuesService(
            new Locale("en-US"), new RandomService());

    public Map<String, String> generateRandomNameAndEmail() {
       String name = fakeValueServise.bothify(new Faker().name().firstName());
       String email = fakeValueServise.bothify(name + "##@gmail.com");

        Map<String, String> data = new HashMap<>();
        data.put("name",  name);
        data.put("email", email);
        return data;
    }
    public String generateRandomSsn() {
        String ssn = String.format("%09d", new Random().nextInt(1_000_000_000)); // Generate up to 9 digits
        return ssn;
    }
    public static void main(String[] args) {
        MockData mockData = new MockData();

        Map<String, String> data = mockData.generateRandomNameAndEmail();
       System.out.println(data.get("name"));
       System.out.println(data.get("email"));

        System.out.println(mockData.generateRandomSsn());
    }
}
