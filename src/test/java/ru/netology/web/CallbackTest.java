package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    @Test
    //корректное заполнение полей
    public void correctFillingOfFields() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Александр Пушкин");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=order-success]").getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (в поле ФИ присутствует "Ё")
    public void testedFunctionalityV8() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ёлкин Пчёлкин");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=order-success]").getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (в поле ФИ латиницей)
    public void testedFunctionalityV7() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Vstanya Vanya");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=name].input_invalid .input__sub").getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (в поле ФИ указаны цифры)
    public void testedFunctionalityV6() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Т34");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=name].input_invalid .input__sub").getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (телефон с 8ки)
    public void testedFunctionalityV5() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("89209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=phone].input_invalid .input__sub").getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (указываем двойное фамилия)
    public void testedFunctionalityV4() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин-Толстой Александр");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=order-success]").getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (указываем не полный телефон)
    public void testedFunctionalityV3() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("+792090020");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=phone].input_invalid .input__sub").getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (не указываем имя фамилия)
    public void testedFunctionalityV2() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        //form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=name].input_invalid .input__sub").getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }

    @Test
    //Тестирование функциональности (не указываем телефон)
    public void testedFunctionalityV1() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Пушкин Александр");
        //form.$("[data-test-id=phone] input").setValue("+79209002011");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String actual = $("[data-test-id=phone].input_invalid .input__sub").getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }




}

