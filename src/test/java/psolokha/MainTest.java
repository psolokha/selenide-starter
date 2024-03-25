package psolokha;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
        SelenideLogger.addListener("Allure-Selenide", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true)
                .includeSelenideSteps(true));
    }

    @Test
    @Order(1)
    @DisplayName("Main Simple Test")
    @Step("Check BookStore main page")
    public void mainRunner() {
        open("https://demoqa.com/books");
        $(".rt-tbody").shouldBe(visible);
        $$(".rt-tr-group").forEach(MainTest::printToConsole);
    }

    @Order(2)
    @DisplayName("Login Test")
    @ParameterizedTest(name = " (userName: {0})")
    @CsvFileSource(resources = "/data.csv")
    @Step("Authorization")
    public void login(String userName, String password) {
        open("https://demoqa.com/login");
        $(By.tagName("h2")).shouldBe(visible).shouldHave(text("Welcome,"));
        $(By.id("userName")).sendKeys(userName);
        $(By.id("password")).sendKeys(password);
        $(By.id("login")).click();
        System.out.println($(By.id("userName-value")).getText());
        $(".rt-tbody").shouldBe(visible);
    }

    private static void printToConsole(SelenideElement a) {
        System.out.println(a.getText());
    }
}