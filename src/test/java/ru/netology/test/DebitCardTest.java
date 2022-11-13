package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardTest {
    SelenideElement form = $(".form");

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }

    @Test
    void happyPathTest() {
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void happyPathTest2(){
        form.$("[data-test-id=name] input").sendKeys("Иванов Иван");
        form.$("[data-test-id=phone] input").sendKeys("+79000000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void happyPathTest3(){
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79000000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    }
