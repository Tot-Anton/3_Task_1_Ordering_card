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


}

