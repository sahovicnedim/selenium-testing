package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
     // private final By addToCartButton = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']"); // Blue Shoes je hardkodirano, ovo je static element, kad bi promijenili blue shoes ne bi radilo pa cemo napravit ovaj element dinamicnim
     private final By viewCartLink = By.cssSelector("a[title='View cart']");
    public StorePage(WebDriver driver) {
        super(driver);
    }

    //posto ostajemo na istoj stranici tj Store page mozemo ovako pisati
    //build pattern je ovo
    public StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);

        return this;
    }

    public StorePage search(String txt){
        enterTextInSearchField(txt).clickSearchButton();
        return this;
    }

    //moze i ovako
  /*  public StorePage search(String txt){
        driver.findElement(searchField).sendKeys(txt);
        driver.findElement(searchButton).click();
        return this;
    } */


    //kad se search izvrsi ostat cemo na istoj stranici, url ce se malo promijeniti ali stranica ostaje ista pa mozemo ovako pisati
    public StorePage clickSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        return this;
    }

    // ne moramo ovo iznad radit ovdje jer vec vraca string
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();

    }

    //ako drzimo ovo private bice dostupno metodama unutar ove klase i ne izvan klase
    private By getAddToCartButtonElement(String productName){

       return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }

    //ne prelazimo na Cart page, ostajemo na store page pa cemo vratit store page ovdje
    public StorePage clickAddToCartButton(String productName){
        // posto moze biti slucajeva da se ovaj element koristi u drugim metodama onda ne moze ovako pisati, moramo napravit metodu
      //  By addToCartButton = By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']"); //dinamicki proizvodimo element i koristimo ga za nasu metodu

        By addToCartButton = getAddToCartButtonElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        return this;
    }

    //ovdje prelazimo na cart page pa pisemo ovako
    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();

        return new CartPage(driver);
    }

}
