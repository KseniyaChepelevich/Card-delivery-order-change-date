package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;



@UtilityClass

public class DataGenerator {


    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(faker.address().city(),
                    generateDate(3),
                    faker.name().fullName(),
                    faker.phoneNumber().subscriberNumber(11));
        }
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }





}
