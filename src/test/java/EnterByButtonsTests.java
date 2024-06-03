import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Api.User;
import Api.UserApi;
import Api.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.Stellarburgers.pageObject.LoginPage;
import ru.yandex.Stellarburgers.pageObject.MainPage;
import static java.net.HttpURLConnection.*;
import static Api.ApiUrls.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class EnterByButtonsTests {

    private DriverRule driverRule = new DriverRule();
    private UserApi userApi;
    private User user;
    private String accessToken;
    int statusCode;

    @Rule
    public DriverRule driver = new DriverRule();


    @Before
    public void setUp() {
        userApi = new UserApi();
        user = UserGenerator.random();
        ValidatableResponse response = userApi.createUser(user);
        statusCode = response
                .extract()
                .statusCode();
        assertThat(statusCode, equalTo(HTTP_OK));
        accessToken = response.extract().path("accessToken");
    }


    @After
    public void deleteUser() {
        try {
            ValidatableResponse deleteResponse = userApi.deleteUser(accessToken);
            deleteResponse
                    .statusCode(HTTP_ACCEPTED)
                    .body("success", equalTo(true), "message", equalTo("User successfully removed"));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Войти профиль по кнопке «Войти в аккаунт» на главной странице")
    @Description("Log in using the log in account button on the main page")
    public void LogInUsingLogInAccountButtonOnTheMainPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается надпись «Оформить заказ» на кнопке в корзине", actual, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Войти в профиль по кнопке «Личный кабинет»")
    @Description("Sign in with button personal account")
    public void signInWithButtonPersonalAccountTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();

        new LoginPage(driver)
                .clickSignInButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается надпись «Оформить заказ» на кнопке в корзине", actual, equalTo("Оформить заказ"));
    }


    @Test
    @DisplayName("Войти в профиль по «Войти» в форме регистрации")
    @Description("Sign in with button on registration page")
    public void singInWithButtonOnRegistrationPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        new LoginPage(driver)
                .clickRegistrationButton()
                .clickSignInButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается надпись «Оформить заказ» на кнопке в корзине", actual, equalTo("Оформить заказ"));
    }


    @Test
    @DisplayName("Войти в профиль по «Войти» в форме восстановления пароль")
    @Description("Sing in with button on forgot password page")
    public void singInWithButtonOnForgotPasswordPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        new LoginPage(driver)
                .clickForgotPasswordButton();
        new LoginPage(driver)
                .clickSignInButtonRememberedPassword()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается надпись «Оформить заказ» на кнопке в корзине", actual, equalTo("Оформить заказ"));
    }

}

