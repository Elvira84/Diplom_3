package ru.yandex.Stellarburgers.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private String someParam;
    private WebElement webDriver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationPage(WebDriver driver, String someParam) {
        this.driver = driver;
        this.someParam = someParam;
    }

    //Поле Имя
    private final By name = By.xpath("//label[contains(text(),'Имя')]/../input");

    //Поле Email
    private final By email = By.xpath("//label[contains(text(),'Email')]/../input");

    //Поле Пароль
    private final By password = By.xpath("//label[contains(text(),'Пароль')]/../input");

    //Кнопки "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Кнопка "Вход"
    private final By signInButton = By.xpath("//a[text()='Войти']");

    //Локатор информации о некорректном пароле
    private final By errorShortPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    //Метод ввода данных в поле "Имя"
    public RegistrationPage setName(String userName) {
        driver.findElement(name).sendKeys(userName);
        return this;
    }

    //Метод ввода данных в поле "Email"
    public RegistrationPage setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
        return this;
    }

    //Метод ввода данных в поле "Пароль"
    public RegistrationPage setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
        return this;
    }

    //Метод нажатия на кнопку "Зарегистрироваться"
    public void clickRegisterButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        registerButton.click();
    }

    //Метод заполнения полей "Имя", "Email" и "Пароль" на странице регистрации
    public LoginPage registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return new LoginPage(driver);
    }

    //Метод нажатия на кнопку "Войти"
    public LoginPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    //Метод получения сообщения об ошибке
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement errorShortPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorShortPassword));
        return errorShortPasswordElement.getText();
    }

}
