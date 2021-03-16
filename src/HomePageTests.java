import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
    @Test
    public void applicationTest(){
        Assert.assertEquals(driver
                .getTitle(),"Trello", "The title of the application is not 'Trello'");
    }
}