package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait; //varijabla za webdriverwait, mozemo ih vise napravit npr za long i short cekanje

    //konstruktor, u konstruktoru definira wait strategiju
    public BasePage(WebDriver driver){
        this.driver = driver;
        //moze koristit ovaj wait object u pages object klasama
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String endPoint){
        driver.get("https://askomdch.com" + endPoint);
    }

    //metoda za visekratnu upotrebu, gdje god nam je potrebna
    public void waitForAnimationsToDissapear(By animation){
        //gdje god se nalaze ove animacije za ucitavanje moramo ih naci i rukovati s njima, ako to ne uradimo imat cemo puno gresaka, automation ce biti cudno
        List<WebElement> animations = driver.findElements(animation);
        System.out.println("Animation size: " + animations.size()); //koliko ima animacija onih sto se vrte za ucitavanje
        if(animations.size()>0){
            wait.until(ExpectedConditions.invisibilityOfAllElements(animations)); // ako animacije nestanu unutar 15sec nece baciti exception, ako ne nestanu unutar 15sec bacit ce izuzetak, izuzetak se pojavi kad pokrenemo test ovaj crveni tekst
            System.out.println("ANIMATIONS ARE INVISIBLE"); //web driver je cekao da obje animacije nestanu i onda je kliknuo na place order button, zato sad nemamo exception
        }
        else{   //ako nema animacije
            System.out.println("Animation not found");
        }
    }
}
