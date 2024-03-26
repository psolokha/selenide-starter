package psolokha.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final String url = "/login";

    public SelenideElement
            userNameField = $(By.id("userName")),
            passwordField = $(By.id("password")),
            loginButton = $(By.id("login")),
            newUserButton = $(By.id("newUser")),
            welcomeMessage = $(By.tagName("h5"));

    LoginPage() {
        welcomeMessage.shouldHave(ownText("Login in Book Store"));
    }

    @Step("Ввести логин и пароль: login={login}, password={password}")
    public MainPage enterUsernameAndPassword(String login, String password) {
        userNameField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
        return new MainPage();
    }

    @Step("Открыть страницу авторизации")
    public static MainPage openPage() {
        open(url);
        return new MainPage();
    }

}
