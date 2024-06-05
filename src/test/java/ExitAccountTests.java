import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.Stellarburgers.pageObject.LoginPage;
import ru.yandex.Stellarburgers.pageObject.MainPage;
import ru.yandex.Stellarburgers.pageObject.ProfilePage;
import static Api.ApiUrls.BASE_URI;
import static org.junit.Assert.assertTrue;

public class ExitAccountTests extends TemplateData{
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();


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
