package ru.yandex.Stellarburgers.pageObject;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@AllArgsConstructor
public class MainPage {

    private  WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }



    //Кнопка "Войти в аккаунт"
    private By logInAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //Кнопка "Личный кабинет"
    public static By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    //Вкладка "Булки"
    private final By bansTab = By.xpath(".//div[span[text()='Булки']]");

    //Вкладка "Соусы"
    private final By saucesTab = By.xpath(".//div[span[text()='Соусы']]");

    //Вкладка "Начинки"
    private final By fillingsTab = By.xpath(".//div[span[text()='Начинки']]");

    //Локатор булок
    private final By bun = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");

    //Локатор соусов
    private final By sauces = By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");

    //Локатор начинки
    private final By fillings = By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");

    //Кнопка "Оформить заказ"
    private final By basketButton = By.xpath(".//div[starts-with(@class,'BurgerConstructor_basket__container')]/button");


    public MainPage() {

    }

    //Метод нажатия кнопки "Личный кабинет"
    public LoginPage clickPersonalAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));

        driver.findElement(personalAccountButton).click();
        return new LoginPage(driver);
    }

    //Метод нажатия кнопки "Войти в аккаунт"
    public LoginPage clickLogInAccountButton() {
        driver.findElement(logInAccountButton).click();
        return new LoginPage(driver);
    }

    //Метод нажатия кнопки "Личный кабинет" авторизированным пользователем
     public ProfilePage clickPersonalAccountButtonAuthUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));

        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    //Метод нажатия вкладки "Булка"
    public MainPage clickBunsTab() {
        driver.findElement(bansTab).click();
        return new MainPage(driver);
    }

    //Метод нажатия вкладки "Соусы"
    public MainPage clickSaucesTab() {
        driver.findElement(saucesTab).click();
        return new MainPage(driver);
    }

    //Метод нажатия вкладки "Начинки"
    public MainPage clickFillingsTab() {
        driver.findElement(fillingsTab).click();
        return new MainPage(driver);
    }

    //Метод появления на экране вкладки "Булки"
    public boolean isBunsIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bun));

        return driver.findElement(bun).isDisplayed();
    }

    //Метод появления на экране вкладки "Соусы"
    public boolean isSaucesIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauces));

        return driver.findElement(sauces).isDisplayed();
    }

    //Метод появления на экране вкладки "Соусы"
    public boolean isFillingsIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillings));

        return driver.findElement(fillings).isDisplayed();
    }

    //Метод получения текста кнопки"Оформит заказ"
    public String getBasketButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketButton));
        return driver.findElement(basketButton).getText();
    }


}
