package Tests;
import Map.LandingPageMap;
import Pages.LandingPageMethods;
import Utils.BaseTest;
import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LandingPageTests extends BaseTest {
    LandingPageMap landingPageMap = new LandingPageMap();
    LandingPageMethods methods;
    CommonMethods commonMethods;
    @Test(groups = {"test"})
    public void hideAndShowtest(){
        methods = new LandingPageMethods(driver);
        methods.hideAndShowValidation();
    }
    @Test
    public void tableTest() {
        driver.findElement(landingPageMap.hideButton).click();
        List <WebElement> courseNames = driver.findElements(By.cssSelector("table#product td.course-name"));
        courseNames.forEach(course-> System.out.println(course.getText()));
    }

    @Test(enabled = true, groups = {"test"})
    public void tableTest2() {

        List <WebElement> courseNames = driver.findElements(By.cssSelector("table#product td"));
        courseNames.forEach(course-> System.out.println(course.getText()));
        for(int i=1; courseNames.size()>i;i+=3){
            System.out.println(courseNames.get(i).getText());
        }
    }
    @Test(enabled = true)
    public void newWindowTest() {
        driver.manage().window().minimize();
        WebElement openWindowButton = driver.findElement(By.cssSelector("button#openwindow"));
        String idPantallaPrincipal = driver.getWindowHandle();
        openWindowButton.click();
        openWindowButton.click();
        openWindowButton.click();
        Set<String> windowsIds = driver.getWindowHandles();
        List<String> ventanasSecundarias = windowsIds.stream().filter(ventana -> !ventana.equals(idPantallaPrincipal)).collect(Collectors.toList());
        System.out.println(idPantallaPrincipal);
        System.out.println(ventanasSecundarias.size());
        System.out.println(ventanasSecundarias.get(0));
        driver.switchTo().window(ventanasSecundarias.get(0));
        driver.switchTo().window(idPantallaPrincipal);
        driver.switchTo().window(ventanasSecundarias.get(0));
        driver.switchTo().window(idPantallaPrincipal);
        driver.switchTo().window(ventanasSecundarias.get(0));
        driver.switchTo().window(idPantallaPrincipal);
        driver.switchTo().window(ventanasSecundarias.get(0));
        driver.switchTo().window(idPantallaPrincipal);
    }
    @Test
    public void actionsExamples() throws InterruptedException {
        WebElement typeInput = driver.findElement(By.cssSelector("input#autosuggest"));
        WebElement mouseHoverExample = driver.findElement(By.cssSelector("button#mousehover"));
        WebElement frame = driver.findElement(By.cssSelector("iframe#courses-iframe"));
        Actions a = new Actions(driver);
        Thread.sleep(2000);
        a.keyDown(Keys.SHIFT).sendKeys(typeInput,"sergio").build().perform();
        Thread.sleep(2000);
        a.scrollToElement(frame).build().perform();
        Thread.sleep(2000);
        a.moveToElement(mouseHoverExample).build().perform();
    }
    @Test
    public void iframeExample() {
        WebElement frame = driver.findElement(By.cssSelector("iframe#courses-iframe"));
        driver.switchTo().frame(frame);
        List<WebElement> listCourses = driver.findElements(By.cssSelector("div#course-list>div"));
        List<WebElement> filteredListCourses = listCourses
                .stream()
                .filter(course->  course.findElement(By.cssSelector("h4")).getText().contains("JavaScript for beginners"))
                .collect(Collectors.toList());
        filteredListCourses.get(0).click();
    }
}
