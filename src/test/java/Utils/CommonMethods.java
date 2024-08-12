package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CommonMethods {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Método para hacer clic en un elemento
    public void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Método para enviar texto a un campo
    public void sendText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Método para obtener el texto de un elemento
    public String getElementText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Método para esperar a que un elemento sea visible
    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Método para verificar si un elemento está presente en la página
    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para obtener el título de la página
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Método para navegar a una URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Método para encontrar múltiples elementos
    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Método para hacer aserciones sobre el texto de un elemento
    public boolean verifyElementText(By locator, String expectedText) {
        String actualText = getElementText(locator);
        return actualText.equals(expectedText);
    }

    // Método para hacer aserciones sobre el título de la página
    public boolean verifyPageTitle(String expectedTitle) {
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    // Método para hacer scroll hacia un elemento
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Método para hacer clic en todos los elementos encontrados por un localizador
    public void clickAllElements(By locator) {
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            element.click();
        }
    }

    // Método para obtener texto de todos los elementos encontrados por un localizador
    public List<String> getTextFromElements(By locator) {
        List<WebElement> elements = findElements(locator);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Método para seleccionar un elemento de una lista por texto
    public void selectElementFromListByText(By locator, String text) {
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                element.click();
                break;
            }
        }
    }

    // Método para verificar si un elemento está seleccionado
    public boolean isElementSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    // Método para verificar si un elemento está visible
    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    // Método para verificar si un elemento está habilitado
    public boolean isElementEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }
}
