package psolokha;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {

    @BeforeAll
    public static void applySettings() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
    }

    @Test
    @Order(1)
    @DisplayName("mainTest")
    public void mainRunner() {
        open("https://demoqa.com/books");
        $(".rt-tbody").shouldBe(visible);
        $$(".rt-tr-group").forEach(a -> {
            System.out.println(a.getText());
        });
    }

    @Test
    @Order(2)
    @DisplayName("loginTest")
    public void login() {
        String userName = "prattandwhitney";
        String password = "Prattandwhitney123!";
        open("https://demoqa.com/login");
        $(By.tagName("h2")).shouldBe(visible).shouldHave(text("Welcome,"));
        $(By.id("userName")).sendKeys(userName);
        $(By.id("password")).sendKeys(password);
        $(By.id("login")).click();
        $(".rt-tbody").shouldBe(visible);
    }
}