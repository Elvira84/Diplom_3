import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Api.User;
import Api.UserApi;
import Api.UserGenerator;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.Stellarburgers.pageObject.MainPage;
import ru.yandex.Stellarburgers.pageObject.RegistrationPage;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;
import static Api.ApiUrls.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTests {
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
    @DisplayName("Успешная регистрация пользователя")
    @Description("Successful user registration")
    public void successfulUserRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        String actual = new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword())
                .signInUser(user.getEmail(), user.getPassword())
                .getBasketButtonText();
        assertThat("Ожидается надпись «Оформить заказ» на кнопке в корзине", actual, equalTo("Оформить заказ"));

    }

    @Test
    @DisplayName("Ошибка при пароле менее 6 символов")
    @Description("Registration with invalid password")
    public void registrationWithInvalidPasswordTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);




        new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword().substring(0, 4));

        String actual = new RegistrationPage(driver).getErrorMessage();

        MatcherAssert.assertThat("Некорректное сообщение об ошибке", actual,equalTo("Некорректный пароль"));


    }

}
