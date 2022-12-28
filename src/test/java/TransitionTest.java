import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class TransitionTest {

    Browser browser;

    @Parameterized.Parameter
    public String Browser;

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{{"Chrome"}, {"Yandex"}};
    }

    @Before
    public void setUp() {
        browser = new Browser(Browser);
        HomePage main = open(Config.MAIN_PAGE_URL, HomePage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(Config.BASIC_EMAIL, Config.BASIC_PASSWORD);
    }

    @After
    public void cleanUp() {
        browser.tearDown();
    }

    @Test
    @DisplayName("Переход на страницу личного кабинета с главной страницы")
    public void checkTheTransitionToThePersonalAccountPage() {
        HomePage main = page(HomePage.class);
        main.clickPersonalAccountButton();
        ProfilePage personalPage = page(ProfilePage.class);
        personalPage.waitAfterTransition();
        assertEquals("После нажатия на кнопку пользователь должен попасть на страницу личного кабинета!", Config.ACCOUNT_PAGE_URL, url());
    }

    @Test
    @DisplayName("Переход на главную страницу после нажатия кнопки конструктора")
    public void checkTheTransitionConstructorButton() {
        HomePage main = page(HomePage.class);
        main.clickPersonalAccountButton();
        ProfilePage personalPage = page(ProfilePage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheConstructorButton();
        assertEquals("После нажатия на кнопку конструктор пользователь должен попасть на главную страницу!", Config.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Переход на главную страницу после нажатия на логотип")
    public void checkTheTransitionLogo() {
        HomePage main = page(HomePage.class);
        main.clickPersonalAccountButton();
        ProfilePage personalPage = page(ProfilePage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogo();
        assertEquals("После нажатия на логотип пользователь должен попасть на главную страницу!", Config.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Переход на страницу входа после нажатия на кнопку выхода из системы")
    public void checkTheTransitionLogOut() {
        HomePage main = page(HomePage.class);
        main.clickPersonalAccountButton();
        ProfilePage personalPage = page(ProfilePage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogOutButton();
        personalPage.waitAfterLogOut();
        assertEquals("После нажатия на кнопку выхода пользователь должен попасть на страницу входа!", Config.LOGIN_PAGE_URL, url());
    }

}