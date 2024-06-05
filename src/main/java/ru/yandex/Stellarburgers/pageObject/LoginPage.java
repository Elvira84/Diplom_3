package ru.yandex.Stellarburgers.pageObject;

import lombok.AllArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class LoginPage {

    private String someParam;
    private  WebDriver driver;

    //Кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");

    //Кнопка "Войти"
    private final By signInButton = By.xpath(".//button[text()='Войти']");

    //Кнопка "Войти" в восстановлении пароля
    private final By signInButtonRememberedPassword = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Войти')]");

    //Поле Email
    private final By email = By.xpath("//label[contains(text(),'Email')]/../input");

    //Поле Пароль
    private final By password = By.xpath("//label[contains(text(),'Пароль')]/../input");

    //Кнопка "Личный кабинет"
    private final By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    //Кнопка "Восстановить пароль"
    private final By forgotPasswordButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Восстановить пароль')]");
    //Кнопка "Конструктора"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage(WebDriver driver, String someParam) {
        this.driver = driver;
        this.someParam = someParam;
    }

    // Метод нажатия на кнопку "Зарегистрироваться"
    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new RegistrationPage(driver);
    }

    //Метод нажатия на кнопку "Войти"
    public LoginPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return this;
    }
    //Метод нажатия на кнопку "Войти" при ваосстановлении пароля
    public LoginPage clickSignInButtonRememberedPassword() {
        driver.findElement(signInButtonRememberedPassword).click();
        return this;
    }

    //Метод передачи Email
    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    //Метод передачи Password
    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    //Метод нажатия кнопки "Личный Кабинет"
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    //Метод нажатия на кнопку "Восстановить пароль"
    public ForgotPassPage clickForgotPasswordButton() {

        WebElement element = driver.findElement(forgotPasswordButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        driver.findElement(forgotPasswordButton).click();

        return new ForgotPassPage(driver);
    }

    //Метод заполнения полей "Email" и "Password" на главной странице
    public MainPage signInUser(String userEmail, String userPassword){

        clickPersonalAccountButton();
        setEmail(userEmail);
        setPassword(userPassword);
        clickSignInButton();

        return new MainPage(driver);
    }

    //Метод нахождения кнопки "Войти"
    public boolean isSignInButtonDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));

        return driver.findElement(signInButton).isDisplayed();
    }

}