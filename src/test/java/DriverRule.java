import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverRule extends ExternalResource {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }


    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            initYandex();
        } else {
            initChrome();
        }
    }

    private void initYandex() {
        WebDriverManager.chromedriver().driverVersion("122.0.6261.128").setup();
        ChromeOptions options = new ChromeOptions().setBinary("C:\\Users\\allay1984\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);

    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

}