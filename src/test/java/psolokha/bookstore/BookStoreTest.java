package psolokha.bookstore;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import psolokha.pages.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

@Tag("bookStore")
@Severity(SeverityLevel.MINOR)
@Owner("P.Solokha")
@Link(name="BookStore", url="https://demoqa.com/books")
@DisplayName("Bookstore testing")
public class BookStoreTest extends BaseUITest {

    @BeforeAll
    public void setBasePage() {
        baseUrl = "https://demoqa.com";
        open(baseUrl);
    }

    @DisplayName("Проверка авторизации на странице BookStore")
    @ParameterizedTest(name = "Login: {0}")
    @CsvSource({"prattandwhitney,Prattandwhitney123!"})
    public void checkBookStoreAuthorization(String login, String password) {
        MainPage.openPage()
                .openLoginPage()
                .enterUsernameAndPassword(login, password)
                .checkSearchFieldIsVisible();
    }


    @DisplayName("Проверка поля поиска книг")
    @ParameterizedTest(name = "Book Name: {0}")
    @ValueSource(strings = {"Speaking JavaScript"})
    public void checkSearchField(String bookName) {
        MainPage.openPage()
                .checkSearchFieldIsVisible()
                .fillSearchField(bookName)
                .checkBookExists(bookName);
    }

    @DisplayName("Проверка фильтра количества отображаемых книг")
    @ParameterizedTest(name = "List Size: {0}")
    @ValueSource(ints = {5,10})
    public void checkBookListSize(Integer num) {
        MainPage.openPage()
                .changeVisibleBooks(num)
                .checkListSize(num);
    }
}