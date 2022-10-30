package org.selenium.pom.base;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    // @Parameters("browser") //ne mozemo koristit sa junit //za biranje pokretanja testiranja iz testng preko chroma ili mozille
    //argument browser je za ovo pokretanje chrome ili mozila iz testng
    // @BeforeMethod //testng
    //  public void startDriver(String browser) {
    //       driver = new DriverManager().initializeDriver(browser);
    //}

    // @Before //junit4 anotacija
    @BeforeEach //junit5
    public void startDriver() {
            String browser ="CHROME"; //izvrsenje testiranja preko firefoxa koristeci junit
             driver = new DriverManager().initializeDriver(browser);
    }


    // @AfterMethod testng
    //@After //junit4
    @AfterEach //junit5
    public void quitDriver() {
            driver.quit();
    }

}
