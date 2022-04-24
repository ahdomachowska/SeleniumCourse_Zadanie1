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

    @Then("new address is added")
    public void newAddressIsAdded() {
        WebElement newAddressIsAdded = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        System.out.println(newAddressIsAdded.getText());
        Assertions.assertEquals(newAddressIsAdded.getText(), "Address successfully added!");

        WebElement firstAlias = driver.findElement(By.cssSelector("#address-24480 > div.address-body > h4"));
        System.out.println(firstAlias.getText());
        Assertions.assertEquals(firstAlias.getText(), "alfons2");

       /* WebElement firstNameInput = driver.findElement(By.xpath("//*[@id=\"address-24256\"]/div[1]/address/text(Alfons Kawka)[1]"));
        System.out.println(firstNameInput.getText());
        Assertions.assertEquals(firstNameInput.getText(), "Alfons Kawka");*/

        //driver.find_elements_by_xpath('//div[contains(text(), "' + text + '")]')

    }
}



