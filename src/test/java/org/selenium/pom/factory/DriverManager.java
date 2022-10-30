package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

import java.time.Duration;

public class DriverManager {

    public WebDriver initializeDriver(String browser){
    //cachePath napravi folder Drivers vamo lijevo i downloadat ce chromedriver executable u ovaj folder i koristit ce ga, na ovaj nacin znamo da webdrivermanager stvarno radi, ovo 106.0 je verija chrome browsera i za tu verziju koristi taj chrome driver u folderu drivers
    //ako se verzija browsera promijeni kreirat ce drugi folder ispod i downloadovat chromedriver.exe
      //  WebDriverManager.chromedriver().cachePath("Drivers").setup(); //posto koristimo chrome driver selektovat cemo chromedriver(). Ova linija koda ce uradit magiju za nas tj. provjerava verziju browsera instaliranog na nasem racunaru(chrome, firefox), poklapa se sa verzijom drivera, daje kompatibilnost izmedju verzije browsera i verzije chrome drivera
       // WebDriver driver = new ChromeDriver();
        //preko ove dvije linije koda pokrece testiranje aplikacije preko firefoxa
      //  WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
      //  WebDriver driver = new FirefoxDriver();
      //  driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //TESTIRANJE PREKO TESTNG.XML, DA IZABERES PREKO FIREFOX ILI CHROME, POSTO SMO U TESTNG.XML DODALI OBA SLUCAJA ZA FIREFOX I CHROME POKRENUCE CHROME I FIREFOX JEDNO ZA DRUGIM
        WebDriver driver;
        String localBrowser;
        localBrowser = browser;
        switch(DriverType.valueOf(localBrowser)){
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                driver = new FirefoxDriver();
            }
            default ->  throw new IllegalStateException("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

}
