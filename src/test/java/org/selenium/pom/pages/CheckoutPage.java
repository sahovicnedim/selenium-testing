package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderButton = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.cssSelector(".showlogin");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[value='Login']");
    private final By animation = By.cssSelector(".blockUI.blockOverlay");
    private final By countyDropDown = By.id("billing_country"); // selektirat cemo country na checkout stranici tj. nije vazno koja drzava je selektirana uvijek cemo selektirat zeljenu drzavu
    private final By stateDropDown = By.id("billing_state"); // selektirat cemo state na checkout stranici, u jsonu kreiramo 2 fieldsa, jedan za country 1 za state
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By directBankTransferRadioButton = By.id("payment_method_bacs"); //trebamo selektovat direct bank transfer radio button ako vec nije selectovan, ako je selektovan ne trebamo nista radit

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

   //gdje god nadje driver.findelement koristit ce wait.until metod da eksplicitno ceka nekoliko sekundi za taj element
    public CheckoutPage enterFirstName(String firstName){
        //kolko sam skonto ovo wait je da se priceka odredjeno vrijeme(nekoliko sekundi) i onda se dalje radi logika
        //explicit wait, posto imamo dvije operacije clear i sendkeys pravimo web element i koristimo ga da izvrsi te operacije
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

        public CheckoutPage enterLastName(String lastName){
            WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
            e.clear();
            e.sendKeys(lastName);

        return this;
    }
    //metoda za selektovanje drzave
    //sa ovom metodom mozemo selektovat drzavu iz drop downa
    public CheckoutPage selectCountry(String countryName){
        //selenium webdriver ima klasu koja dozvoljava lagan rad sa dropdownom
        //trebamo kreirat object of that class
        //kao argument trebamo dodati drop down element koj smo kreirali, treba uradit driver.findElement pa onda drop down element
        //Select select = new Select(driver.findElement(countyDropDown));
        //kad odemo na inspect na stranici i prikazemo sve drzave koje mozemo selektovat, value je country code a visible text je ime drzave
        //posto smo uzeli countryName kao argument koristit cemo visible text
       // select.selectByVisibleText(countryName);

        //select klasa ne radi za firefox samo za chrome pa mijenjamo kod da radi za oba browsera
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click(); //kliknemo na dropdown menu da opcije budu vidljive
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']"))); //option element tj nas element koji zadamo
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e); //pomocu ovog skrolamo do naseg elementa
        e.click(); // onda kliknemo na njeg
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneField));
        e.clear();
        e.sendKeys(addressLineOne);

        return this;
    }

    public CheckoutPage enterCity(String city){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityField));
        e.clear();
        e.sendKeys(city);

        return this;
    }

    //metoda za selektovanje state
    //sa ovom metodom mozemo selektovat state iz drop downa
    public CheckoutPage selectState(String stateName){
        //selenium webdriver ima klasu koja dozvoljava lagan rad sa dropdownom
        //trebamo kreirat object of that class
        //kao argument trebamo dodati drop down element koj smo kreirali, treba uradit driver.findElement pa onda drop down element
       // Select select = new Select(driver.findElement(stateDropDown));
        //kad odemo na inspect na stranici i prikazemo sve state koje mozemo selektovat, value je country code a visible text je ime drzave
        //posto smo uzeli stateName kao argument koristit cemo visible text
       // select.selectByVisibleText(stateName);

        //select klasa ne radi za firefox samo za chrome pa mijenjamo kod da radi za oba browsera
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }
    public CheckoutPage enterPostCode(String postCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeField));
        e.clear();
        e.sendKeys(postCode);

        return this;
    }

    public CheckoutPage enterEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailField));
        e.clear();
        e.sendKeys(email);

        return this;
    }

    //functional method da postavi billing address
    //uzima billing address object kao argument
    //unutra metode mozemo pozvati druge metode ili komande unutar metode, pozvat cemo metode
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
      // trebamo vratit cheoucktout page
      return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostCode()).
                enterEmail(billingAddress.getEmail());
    }

    //ovdje vec ceka da animacija nestane i misli da ne treba explicit wait za ovaj button
    public CheckoutPage placeOrder(){
        waitForAnimationsToDissapear(animation);
        driver.findElement(placeOrderButton).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();

    }

    public CheckoutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUsername(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);

        return this;
    }

    public CheckoutPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    public CheckoutPage login(String username, String password){
      return  enterUsername(username).enterPassword(password).clickLoginButton();
    }

    public CheckoutPage selectDirectBankTransferRadioButton(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioButton));
        //provjerit cemo da li je radio button vec cekiran
        if(!e.isSelected()){
            e.click(); //ako nije cekiran onda cemo kliknut na njeg
        }
        return this;
    }

}
