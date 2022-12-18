import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import PageObject.RegistrationPage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest {

    Browser browser;

    @Parameterized.Parameter
    public String Browser;

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex"}
        };
    }

    @Before
    public void setUp() {
        browser = new Browser(Browser);

    }

    @After
    public void cleanUp() {
        browser.tearDown();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void checkTheCorrectRegistration() {
        final String NAME = "user" + RandomStringUtils.randomAlphabetic(3);
        final String EMAIL = "user" + RandomStringUtils.randomAlphabetic(3) + "@mail.ru";
        RegistrationPage registerPage = open(Config.REGISTER_PAGE_URL, RegistrationPage.class);
        registerPage.registration(NAME, EMAIL, Config.BASIC_PASSWORD);
        registerPage.waitAfterRegistration();
        assertEquals("После успешной регистрации пользователь должен попасть на страницу авторизации",
                url(), Config.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void checkTheRegistrationWithInvalidPassword() {
        final String NAME = "user" + RandomStringUtils.randomAlphabetic(3);
        final String EMAIL = "user" + RandomStringUtils.randomAlphabetic(3) + "@mail.ru";
        RegistrationPage registerPage = open(Config.REGISTER_PAGE_URL, RegistrationPage.class);
        registerPage.registration(NAME, EMAIL, Config.INCORRECT_PASSWORD);
        assertTrue("Должно отображаться сообщение о некорректном пароле", registerPage.checkIncorrectPasswordSign());
    }
}