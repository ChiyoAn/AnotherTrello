import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        Thread.sleep(7000);
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        Thread.sleep(20000);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());

        //----Open QA Haifa8 board ----
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='QA Haifa8']"));
        qaHaifa8Board.click();
        Thread.sleep(3000);

    }
    @Test
    public void createNewList() throws InterruptedException {
        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        Thread.sleep(2000);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField,"titleList");
        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();
    }

    @Test
    public void changeListName() throws InterruptedException {
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            Thread.sleep(2000);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
            Thread.sleep(2000);
        }
        // ----- Define the index of the last list --------------
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        lastHeader.click();
        Thread.sleep(2000);

        //------- Change the header -----------------
        String newHeader = "newHeader1";
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        Thread.sleep(2000);
        lastNameList.sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.navigate().refresh();
        Thread.sleep(2000);

        lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        Assert.assertEquals(lastHeader.getText(), newHeader);
    }


}