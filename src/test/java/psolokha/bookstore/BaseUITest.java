package psolokha.bookstore;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TextReportExtension.class)
public class BaseUITest {
    @BeforeAll
    public  void prepareBrowser() {
        SelenideLogger.addListener("AllureFramework", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }
    @AfterAll
    public void closeWebDriver() {
       WebDriverRunner.driver().close();
    }
}
