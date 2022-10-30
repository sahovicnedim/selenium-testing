package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutButton = By.cssSelector(".checkout-button");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
       //explicit wait
       return  wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();

    }

    //kad kliknem na checkout button prebacit ce me na checkout stranicu pa pisemo ovako

    public CheckoutPage clickCheckoutButton(){
        //explicit wait
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click(); //cekam dugme da bude clickable

        return new CheckoutPage(driver);
    }

}
