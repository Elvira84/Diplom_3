import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.Stellarburgers.pageObject.LoginPage;
import ru.yandex.Stellarburgers.pageObject.MainPage;
import ru.yandex.Stellarburgers.pageObject.ProfilePage;

import static Api.ApiUrls.BASE_URI;
import static org.hamcrest.CoreMatchers.containsString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SwitchToAndFromPersonalAccountTests extends TemplateData {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();


    @Test
    @DisplayName("Переход в личный кабинет авторизованного пользователяпо клику на «Личный кабинет»")
    @Description("Switch to personal account auth user")
    public void SwitchToPersonalAccountAuthUserTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        new ProfilePage(driver)
                .isAccountTextDisplayed();
        assertThat("Некоректный URL страницы Личного кабинета", driver.getCurrentUrl(), containsString("/profile"));

    }

    @Test
    @DisplayName("Переход в личный кабинет неавторизованного пользователя по клику на «Личный кабинет»")
    @Description("Switch to personal account unauth user")
    public void SwitchToPersonalAccountUnAuthUserTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        Boolean actual = new LoginPage(driver)
                .isSignInButtonDisplayed();
        assertTrue("Личный кабинет не открывается у неавторизованного пользователя", actual);

    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @Description("Switch from personal account to constructor")
    public void SwitchFromPersonalAccountToConstructorTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        Boolean actual = new ProfilePage(driver)
                .clickConstructorButton()
                .isBunsIsDisplayed();
        assertTrue("Конструктор не открывается", actual);

    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Switch from personal account to constructor  to logo")
    public void SwitchFromPersonalAccountToConstructorToLogoTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        Boolean actual = new ProfilePage(driver)
                .clickLogoButton()
                .isBunsIsDisplayed();
        assertTrue("Главная страница не открывается", actual);

    }


}
