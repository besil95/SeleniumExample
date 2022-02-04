package driver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static constants.ExampleConstants.BASE_URL;

public class Driver {

    public static WebDriver webDriver;
    final static Logger logger = Logger.getLogger(String.valueOf(Driver.class));

    @Before
    public void initializeDriver() {
        webDriver = DriverFactory.getDriver();
        logger.info("Starting tests");
        webDriver.get(BASE_URL);
    }


    @After
    public void closeDriver() {
        webDriver.quit();
        logger.info("Tests are over.");
    }
}
