package org.selenium.pom.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;


import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;



public class MyFirstTestCase extends BaseTest {


  // @Test anotacija za testng
   // @Test //junit
    @Test
    public void firstTest() throws IOException {
      //  System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Desktop\\Selenium\\chromedriver.exe");
      //  WebDriver driver = new ChromeDriver();

        // page object
        //kad kreiramo page object trebamo koristi new keyword i kreirat new object
    //    HomePage homePage = new HomePage(driver);
        //ne moramo koristi new keyword za store page jer koristimo fluent interface
    //   StorePage storePage = homePage.clickStoreMenuLink(); // return value of this method je StorePage otkad koristimo fluent interface pa ne moramo kreirati object of the Store page pa mozemo samo reci ovako kako pise

        //build pattern
        //ovo je primjer structural page object
      /*  storePage.
                enterTextInSearchField("Blue").
                clickSearchButton();
        */
        //build pattern
        //primjer functional page object

     //   storePage.search("Blue");


        //builder pattern
        //moze i ovako ali koristimo ovdje ovaj ispod postupak
    /*    BillingAddress billingAddress = new BillingAddress(). //kreiranje objekta of Billing address
        setFirstName("Edin").
        setLastName("Skender").
        setAddressLineOne("Branilaca Sarajeva").
        setCity("NYC").
        setPostCode("90267").
        setEmail("edosk@gmail.com");
*/
        // ovo su hard kodirani podaci edin skender... mozemo ih citati iz nekog fajla a to je json fajl
     // BillingAddress billingAddress = new BillingAddress("Edin", "Skender", "Branilaca Sarajeva", "NYC", "90267", "edosk@gmail.com");
        String searchFor ="Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class); //dodajemo json object, pretvaramo ga u java object (BillingAddress billingAddress)
        Product product = new Product(1215); //new object of product, dodajemo product id koji trebamo izvuci iz jsona i sad mozemo koristit ovaj product object da dobijemo product name

        StorePage storePage = new HomePage(driver).
                load().
                clickStoreMenuLink().
                search("Blue");

       // Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”"); testng
        //org.junit.Assert.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle()); //junit4
        Assertions.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle()); //junit5
        storePage.clickAddToCartButton(product.getName()); //ovaj test case sad moze radit sa bilo kojim productom

        CartPage cartPage = storePage.clickViewCart();

     //   Assert.assertEquals(
            //    cartPage.getProductName(), product.getName()
    //    );
     //   org.junit.Assert.assertEquals(product.getName(),  cartPage.getProductName()); //junit4
        Assertions.assertEquals(product.getName(),  cartPage.getProductName()); //junit5

        //mozemo otic u checkout page i kreirat functional method
        CheckoutPage checkoutPage = cartPage.
                clickCheckoutButton(). // trebamo kliknut na checkout button i vratit ce checkout page
               setBillingAddress(billingAddress).
                selectDirectBankTransferRadioButton().
                placeOrder();


       // Assert.assertEquals(
              //  checkoutPage.getNotice(),
             //   "Thank you. Your order has been received."
       // );
       // org.junit.Assert.assertEquals( "Thank you. Your order has been received.",  checkoutPage.getNotice()); //junit4
        Assertions.assertEquals("Thank you. Your order has been received.",  checkoutPage.getNotice()); //junit5



    }
    
      @Test //junit5
    public void secondTest() throws InterruptedException {
        //  System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Desktop\\Selenium\\chromedriver.exe");
        //  System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Desktop\\Selenium\\chromedriver.exe");
        //  WebDriver driver = new ChromeDriver();


        // page object
        //kad kreiramo page object trebamo koristi new keyword i kreirat new object
    //    HomePage homePage = new HomePage(driver);
        //ne moramo koristi new keyword za store page jer koristimo fluent interface
      //  StorePage storePage = homePage.clickStoreMenuLink(); // return value of this method je StorePage otkad koristimo fluent interface pa ne moramo kreirati object of the Store page pa mozemo samo reci ovako kako pise

        //build pattern
        //ovo je primjer structural page object
      /*  storePage.
                enterTextInSearchField("Blue").
                clickSearchButton();
        */
        //build pattern
        //primjer functional page object
     //   storePage.search("Blue");

        StorePage storePage = new HomePage(driver).
                load().
                clickStoreMenuLink().
                search("Blue");
      //  Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
       // org.junit.Assert.assertEquals("Search results: “Blue”", storePage.getTitle());
          Assertions.assertEquals("Search results: “Blue”", storePage.getTitle());
        storePage.clickAddToCartButton("Blue Shoes"); //ovaj test case sad moze radit sa bilo kojim productom
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();

       // Assert.assertEquals(
            //    cartPage.getProductName(), "Blue Shoes"
        //);
      //  org.junit.Assert.assertEquals("Blue Shoes", cartPage.getProductName());
        Assertions.assertEquals("Blue Shoes", cartPage.getProductName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton(); // trebamo kliknut na checkout button i vratit ce checkout page
        checkoutPage.clickHereToLoginLink();

        Thread.sleep(2000);
        checkoutPage.
                login("user123456", "123456").
                enterFirstName("Edin").
                enterLastName("Skender").
                selectCountry("India").
                enterAddressLineOne("Branilaca Sarajeva").
                enterCity("NYC").
                selectState("Maharashtra").
                enterPostCode("902670").
                enterEmail("edosk@gmail.com").
                selectDirectBankTransferRadioButton().
                placeOrder();
        Thread.sleep(2000);

      //  Assert.assertEquals(
             //   checkoutPage.getNotice(),
              //  "Thank you. Your order has been received."
       // );

       // org.junit.Assert.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice());
          Assertions.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice());






    }


}
