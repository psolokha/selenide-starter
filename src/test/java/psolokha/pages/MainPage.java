package psolokha.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final String url = "/books";
    public SelenideElement
            login = $(By.id("login")),
            searchField = $(By.id("searchBox")),
            leftPanel = $(".left-pannel"),
            bookListHeader = $(".rt-thead"),
            bookListFooter = $(".pagination-bottom"),
            booksListElement = $(".rt-tbody"),
            rowsNumElement = $x("//select[@aria-label='rows per page']");

    public ElementsCollection
            booksList = booksListElement.$$(".rt-tr-group"),
            rowNumList = rowsNumElement.findAll(By.tagName("option"));


    public MainPage() {
        searchField.shouldBe(visible);
    }

    @Step("Открыть главную страницу")
    public static MainPage openPage() {
        open(url);
        return new MainPage();
    }

    @Step("Нажать кнопку Login и перейти на страницу авторизации")
    public LoginPage openLoginPage() {
        login.click();
        return new LoginPage();
    }

    @Step("Проверить, что поле поиска доступно на странице")
    public MainPage checkSearchFieldIsVisible() {
        searchField.shouldBe(visible);
        return this;
    }

    @Step("Ввести в поле поиска: {text}")
    public MainPage fillSearchField(String text) {
        searchField.sendKeys(text);
        return this;
    }

    @Step("Проверить, что в списке есть книга: {bookName}")
    public MainPage checkBookExists(String bookName) {
        booksList.first()
                .$(By.tagName("a"))
                .shouldHave(text(bookName));
        return this;
    }

    @Step("Выбрать количество отображаемых книг в списке")
    public MainPage changeVisibleBooks(Integer num) {
        bookListFooter.scrollTo();
        rowsNumElement.shouldBe(visible).click();
        rowNumList.findBy(text(num + " rows"))
                .click();
        return this;
    }

    @Step("Проверить, что список содержит {num} книг")
    public MainPage checkListSize(Integer num) {
        bookListHeader.scrollTo();
        booksList.shouldHave(size(num));
        return this;
    }
}
