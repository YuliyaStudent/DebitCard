package ru.netology.test;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardValidationTest {
    SelenideElement form = $(".form");

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }
    @Test
    void latinLetterInNameTest() {
        form.$("[data-test-id=name] input").setValue("Ivanov Иван");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void numberInNameTest() {
        form.$("[data-test-id=name] input").setValue("Иванов 1");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void symbolInNameTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван%");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void emptyFieldNameTest() {
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void mobileNumberWithoutPlusTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void lettersInMobileNumberTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("7900m000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void tenNumbersInMobileNumberTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+7900000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void emptyFieldInMobileNumberTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void noAgreementTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("button").click();
        $("[data-test-id=agreement]").should(cssClass("input_invalid"));
    }

}

