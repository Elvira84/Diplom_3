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
import ru.yandex.Stellarburgers.pageObject.ProfilePage;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;
import static Api.ApiUrls.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ExitAccountTests {
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
    @DisplayName("Выход из акаунта по кнопке «Выйти» в личном кабинете")
    @Description("Exit account with button Exit")
    public void exitAccountWithButtonExit() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton()
                .signInUser(user.getEmail(), user.getPassword())
                .clickPersonalAccountButton();

       new ProfilePage(driver)
                .clickExitButton();

        Boolean actual = new LoginPage(driver)
                .isSignInButtonDisplayed();

        assertTrue("Выход из акаунта не совершен", actual);

    }

}
