package base;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BaseMethods {

    WebDriver driver = null;
    WebDriverWait wait = null;
    final static Logger logger = Logger.getLogger(String.valueOf(BaseMethods.class));


    public BaseMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        logger.info("Element " + by + " is visible and clickable");
        return driver.findElement(by);
    }

    public boolean notFindElement(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            logger.info("Element " + by + " is visible and clickable");
            return false;
        } catch (Exception ignored) {
            return true;
        }
    }

    public void hoverElement(By by) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(findElement(by)).build().perform();
            logger.info("Hovered over object " + by);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed to hover over object " + by);
        }
    }

    public void clickElement(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            findElement(by).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement checkVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }


    public String getTextElement(By by) {
        return ((WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by))).getText();
    }

    public void getSendKeyElement(By by, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            findElement(by).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean checkURLTitle(String title) {
        String Actual = driver.getTitle();

        if (Actual.equals(title)) {
            logger.info(title + " : title successful checked");
            return true;
        } else {
            logger.info(title + " : title failed");
            return false;
        }

    }

    public String colorVerify(By by, String cssValue) {
        String colorCode = findElement(by).getCssValue(cssValue);
        return Color.fromString(colorCode).asHex();

    }

    public void switchToPageElement(int pageIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(pageIndex));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchWindow() {
        try {
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollToWindowWithIndex(String scroll) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + scroll + ")");
        logger.info("Page scrolled down by " + scroll);
    }

    public Boolean checkCurrentUrl(String key) {
        String url = driver.getCurrentUrl();
        if (key.contains(" ")) {
            {
                String[] keyList = key.split(" ");
                key = keyList[0];
            }
        }
        if (url.contains(key)) {
            logger.info("Searched word of ' " + key + " ' is found");
            return true;
        } else {
            logger.info("Searched word of '' " + key + " '' is not found");
            return false;
        }
    }

}
