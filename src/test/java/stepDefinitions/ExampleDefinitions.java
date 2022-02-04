package stepDefinitions;

import base.BaseMethods;
import org.openqa.selenium.WebDriver;


import static locators.ExampleLocators.*;

public class ExampleDefinitions extends BaseMethods {
    public ExampleDefinitions(WebDriver driver){
        super(driver);
    }

    public ExampleDefinitions menu() {
        clickElement(MENU);
        return this;
    }

    public ExampleDefinitions categories() {
        checkVisible(AWARDS);
        hoverElement(OSCARS);
        return this;
    }

}
