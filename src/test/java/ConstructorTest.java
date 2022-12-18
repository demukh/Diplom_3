import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import PageObject.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest {

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
    @DisplayName("Проверка добавления в корзину элемента  из раздела Булки")
    public void checkTransitionOfBunsButton() {
        HomePage main = open(Config.MAIN_PAGE_URL, HomePage.class);
        assertTrue("В корзине заказа должна быть видна добавленная булка", main.clickBunsButtonCheckTheSign());
    }

    @Test
    @DisplayName("Проверка добавления в корзину элемента из раздела Соусы")
    public void checkTransitionOfSaucesButton() {
        HomePage main = open(Config.MAIN_PAGE_URL, HomePage.class);
        assertTrue("В корзине заказа должен быть виден добавленный соус", main.clickSaucesButtonAndCheckTheSign());
    }

    @Test
    @DisplayName("Проверка добавления в корзину элемента из раздела Начинки")
    public void checkTransitionOfFillingButton() {
        HomePage main = open(Config.MAIN_PAGE_URL, HomePage.class);
        assertTrue("В корзине заказа должна быть видна добавленная начинка", main.clickFillingButtonAndCheckTheSign());
    }

}