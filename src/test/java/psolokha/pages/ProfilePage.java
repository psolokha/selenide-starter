package psolokha.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private static final String url = "/profile";

    public SelenideElement searchField  = $(By.id("searchBox"));

    ProfilePage() {
        searchField.shouldBe(visible);
    }

    @Step("Открыть страницу профиля")
    public static ProfilePage openPage() {
        open(url);
        return new ProfilePage();
    }

}
