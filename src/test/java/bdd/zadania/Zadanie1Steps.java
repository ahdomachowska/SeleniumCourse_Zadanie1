package bdd.zadania;

import PageObjectPattern.myStorePage.AuthenticationPage;
import PageObjectPattern.myStorePage.CreateNewAddressPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;


public class Zadanie1Steps {

    private WebDriver driver;

    @Given("user is logged in on account page")
    public void userIsLoggedInOnAccountPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.logIn("alfonskawka88@email.com", "Admin1234");

    }

    @When("user click tile addresses")
    public void userClickTileAddresses() {
        driver.findElement(By.id("addresses-link")).click();
    }

    @When("user click create new address")
    public void userClickCreateNewAddress() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a/span")).click();
    }

    @When("user fills from with data {string}, {string}, {string}, {string}, {string}, {string}")
    public void userFillsFromWithData(String alias, String address, String city, String zipPostalCode, String country, String phone) {
        CreateNewAddressPage createNewAddressPage = new CreateNewAddressPage(driver);
        createNewAddressPage.fillForm(alias,
                address,
                city,
                zipPostalCode,
                country,
                phone);
    }

    @And("click Save button to add new address")
    public void clickSaveButtonToAddNewAddress() {
        CreateNewAddressPage createNewAddressPage = new CreateNewAddressPage(driver);
        createNewAddressPage.createAddress();
    }

    @Then("new address is added and deleted")
    public void newAddressIsAddedAndDeleted() {
        WebElement newAddressIsAdded = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        System.out.println(newAddressIsAdded.getText());
        Assertions.assertEquals(newAddressIsAdded.getText(), "Address successfully added!");

        List<WebElement> webElementList = driver.findElements(By.cssSelector("article[id^='address-']")); //napisane na podstawie Attribute Starts with
        webElementList.forEach((tile) -> {

            if (tile.findElement(By.cssSelector("div.address-body > h4")).getText().equals("alfons2")){

                if(tile.findElement(By.cssSelector("div.address-body > address")).getText().contains(""" 
                        Alfons Kawka
                        Downing Street
                        London
                        EC4Y 9AY
                        United Kingdom
                        729888555""")){
                    tile.findElement(By.cssSelector("div.address-footer > a:nth-child(2) > span")).click();
                }

            }

        });
        Assertions.assertEquals(driver.findElement(By.cssSelector("#notifications > div > article > ul > li")).getText(), "Address successfully deleted!");
    }
}

// potrojny cudzyslow porownoje stringi wraz ze znakami konca linii, chyba od javy 11 tak można robić



