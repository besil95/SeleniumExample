package tests;

import driver.Driver;
import org.junit.Before;
import org.junit.Test;
import stepDefinitions.ExampleDefinitions;


public class ExampleTest extends Driver {
    ExampleDefinitions exampleDefinitions;

    @Before
    public void before() {
        exampleDefinitions = new ExampleDefinitions(webDriver);
    }

    @Test
    public void initialTest() {
        exampleDefinitions.menu();
        exampleDefinitions.categories();
    }

}
