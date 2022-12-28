import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.HomePage;
import pageobject.RegistrationPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest {
    HomePage main = page(HomePage.class);
    LoginPage login = page(LoginPage.class);
    RegistrationPage register = page(RegistrationPage.class);
    ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);

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
    public void cleanUp(){
        browser.tearDown();
    }

    @Test
    @DisplayName("Вход с главной страницы через кнопку Войти в аккаунт")
    public void loginFromMainPageButton() {
        open(Config.MAIN_PAGE_URL);
        main.clickAccountEntryButton();
        login.entry(Config.BASIC_EMAIL, Config.BASIC_PASSWORD);
        assertEquals("Пользователь должен попасть на главную страницу",
                Config.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Вход с главной страницы через Личный кабинет")
    public void loginFromAccountButton() {
        open(Config.MAIN_PAGE_URL);
        main.clickPersonalAccountButton();
        login.entry(Config.BASIC_EMAIL, Config.BASIC_PASSWORD);
        assertEquals("Пользователь должен попасть на главную страницу",
                Config.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Вход со страницы регистрации через кнопку Войти")
    public void loginFromRegistrationPage() {
        open(Config.REGISTER_PAGE_URL);
        register.clickTheEntryButton();
        login.entry(Config.BASIC_EMAIL, Config.BASIC_PASSWORD);
        assertEquals("Пользователь должен попасть на главную страницу",
                Config.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Переход со страницы восстановления пароля")
    public void loginFromForgotPasswordPage() {
        open(Config.FORGOT_PASSWORD_URL);
        forgotPasswordPage.clickTheEntryButton();
        login.entry(Config.BASIC_EMAIL, Config.BASIC_PASSWORD);
        assertEquals("Пользователь должен попасть на главную страницу",
                Config.MAIN_PAGE_URL, url());
    }

}