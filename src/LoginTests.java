import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        //Thread.sleep(7000);
        //waitUntilElementIsClickable(By.id("login"),15);
        waitUntilElementIsClickable(By.id("user"),10);
        waitUntilElementIsClickable(By.id("login"),20);


    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        // -------- Enter login/password -------------
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"123");
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,"pass");
        //to be sure that loginField and passwordField are already filled in
        Thread.sleep(1000);

        // -------- Click login button ------------
        waitUntilElementIsClickable(By.id("login"),20);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(3000);
        waitUntilElementIsVisible(By.cssSelector("#error >.error-message"),20);

        // --------- Print error message ----------
        WebElement errorMessage = driver.findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());

        Assert.assertTrue(errorMessage.getText().contains("There isn't an account"),"The error-message" +
                "doesn't contain 'There isn't an account'");

    }

    @Test
    public void loginPositive() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"obertbd@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("password"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("KbyfBydthc94");
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        //Thread.sleep(20000);
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open Boards Menu']"),10);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());
    }

    @Test
    public void negativePasswordIncorrect() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"obertbd@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("incorrect");
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the error-message and print it -------
        Thread.sleep(5000);
        System.out.println("Error-message: " + driver
                .findElement(By.id("login-error")).getText());
    }





}