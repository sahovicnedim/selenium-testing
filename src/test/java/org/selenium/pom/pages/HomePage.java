package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
    // by- klasa iz seleniuma
    //store na naslovnoj je ova varijabla storeMenuLink
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");


        //prosljedjuje driver u konstruktor BasePage
    // driver u BackPajdzu postavlja HomePage
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load(){
        load("/"); //pozovemo load metod iz base page
        return this;
    }

    //metoda za klik na store
    //kad navigiramo na drugu stranicu trebamo vratiti object te stranice
    //ovdje navigiramo na store stranicu pa cemo vratit object od store page
    public StorePage clickStoreMenuLink(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver); //trebamo ovo uradit samo ako navigiramo na novu stranicu, ako cemo ostat na istoj stranici ne moramo ovo uradit, ovo je fluent interface

    }


}
