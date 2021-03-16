import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void startAppl() throws InterruptedException {
        driver = new ChromeDriver();
        //----Driver initialization. Open Trello application
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--lang=" + "rus");
        //driver = new ChromeDriver(options);
        driver.get("https://trello.com/");
        //Thread.sleep(5000);
        waitUntilElementIsClickable(By.xpath("//a[contains(text(),'Log in')]"),40);
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void waitUntilElementIsClickable(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void waitUntilElementIsPresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }
}