import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.hamcrest.MatcherAssert;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.Stellarburgers.pageObject.MainPage;
import ru.yandex.Stellarburgers.pageObject.RegistrationPage;

import static Api.ApiUrls.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTests extends TemplateData{
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

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
