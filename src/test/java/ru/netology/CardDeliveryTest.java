package ru.netology;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;



public class CardDeliveryTest {

    RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");


    //String sPhoneNumber = String.valueOf(Long.parseLong(info.getPhoneNumber()).replaceAll("\\D+", ""));

    //String sPhoneNumber = String.format(info.getPhoneNumber() ).



        public String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;





        @Test
        public void shouldSendCompletedForm() {
            Configuration.holdBrowserOpen = true;

            //String planningDate = generateDate(3);
            open("http://localhost:9999"); //Открыть приложение
            $("[data-test-id=\"city\"] .input__control").setValue(info.getAddress()); //Заполнить поле Город
            $("[data-test-id='date'] .input__control").sendKeys(deleteString);//Заполнить поле Дата встречи спредставителем банка
            $("[data-test-id='date'] .input__control").setValue(info.getDate());
            $x("//*[@name=\"name\"]").setValue(info.getName()); //Заполнить поле Фамилия Имя
            $x("//*[@name=\"phone\"]").setValue("+" + info.getPhoneNumber());//Заполнить поле Мобильный телефон
            $("[data-test-id=\"agreement\"] .checkbox__box").click();//Кликнуть чекбокс
            $(".button__text").click();//Нажать кнопку Забронировать
            //Подождать 15 сек
            $("[data-test-id=\"notification\"]").shouldHave(text("Встреча успешно забронирована на " + info.getDate()), Duration.ofSeconds(15));
            //Получить всплывающее окно с сообщением "Успешно! Встреча успешно забранирована на
            $(".notification__content").shouldBe(visible)
                    .shouldHave(exactText("Встреча успешно забронирована на " + info.getDate()));

            System.out.println(info);


        }




}
