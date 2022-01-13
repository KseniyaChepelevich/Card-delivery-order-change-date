package ru.netology;

import com.codeborne.selenide.Condition;
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


    @Test
    public void shouldSendCompletedFormAndChangeDate() {
        Configuration.holdBrowserOpen = true;
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        String planingDate = DataGenerator.generateDate(3);
        String changeDate = DataGenerator.generateDate(5);

        open("http://localhost:9999"); //Открыть приложение
        $("[data-test-id=\"city\"] .input__control").setValue(info.getAddress()); //Заполнить поле Город
        $("[data-test-id='date'] .input__control").sendKeys(deleteString);//Заполнить поле Дата встречи спредставителем банка
        $("[data-test-id='date'] .input__control").setValue(planingDate);
        $x("//*[@name=\"name\"]").setValue(info.getName()); //Заполнить поле Фамилия Имя
        $x("//*[@name=\"phone\"]").setValue("+" + info.getPhoneNumber());//Заполнить поле Мобильный телефон
        $("[data-test-id=\"agreement\"] .checkbox__box").click();//Кликнуть чекбокс
        $(".button__text").click();//Нажать кнопку Забронировать
        //Подождать 15 сек
        $("[data-test-id=\"success-notification\"]").shouldHave(Condition.text("Встреча успешно запланирована на " + DataGenerator.generateDate(3)), Duration.ofSeconds(15));
        //Получить всплывающее окно с сообщением "Успешно! Встреча успешно забранирована на
        $("[data-test-id=\"success-notification\"] button .icon-button__content").click();
        $("[data-test-id='date'] .input__control").sendKeys(deleteString);
        $("[data-test-id='date'] .input__control").setValue(changeDate);
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();//Нажать кнопку Забронировать
        $("[data-test-id=\"replan-notification\"]").shouldHave(Condition.text("Необходимо подтверждение"));
        $("[data-test-id=\"replan-notification\"] .button__text").shouldHave(Condition.text("Перепланировать")).click();
        $("[data-test-id=\"success-notification\"] ").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно запланирована на " + changeDate));


    }


}
