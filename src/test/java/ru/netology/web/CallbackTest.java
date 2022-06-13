package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    @BeforeEach
    public void openingPage() {
        open("http://localhost:9999");
    }

    @Test
    //корректное заполнение полей
    public void correctFillingOfFields() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Александр Пушкин");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    //Тестирование функциональности (чек-бокс не отмечен)
    public void testedFunctionalityV10() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        //form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=agreement]").should(cssClass("input_invalid"));
    }


    @Test
    //Тестирование функциональности (Указали только фамилия)
    public void testedFunctionalityV9() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    //Тестирование функциональности (в поле ФИ присутствует "Ё")
    public void testedFunctionalityV8() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ёлкин Пчёлкин");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    //Тестирование функциональности (в поле ФИ латиницей)
    public void testedFunctionalityV7() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Vstanya Vanya");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    //Тестирование функциональности (в поле ФИ указаны цифры)
    public void testedFunctionalityV6() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Т34");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    //Тестирование функциональности (телефон с 8ки)
    public void testedFunctionalityV5() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("89209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    //Тестирование функциональности (указываем двойное фамилия)
    public void testedFunctionalityV4() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин-Толстой Александр");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    //Тестирование функциональности (указываем не полный телефон)
    public void testedFunctionalityV3() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("+792090020");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    //Тестирование функциональности (не указываем имя фамилия)
    public void testedFunctionalityV2() {
        SelenideElement form = $(".form");
        //form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    //Тестирование функциональности (не указываем телефон)
    public void testedFunctionalityV1() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        //form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}

