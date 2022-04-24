package PageObjectPattern.myStorePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateNewAddressPage {
    private WebDriver driver;

    @FindBy(name = "alias")
    private WebElement aliasInput;

    @FindBy(name = "address1")
    private WebElement addressInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "postcode")
    private WebElement zipPostalCodeInput;

    @FindBy(name = "id_country")
    private WebElement countryInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/footer/button")
    private WebElement saveButton;

    public CreateNewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String alias, String address, String city, String zipPostalCode, String country, String phone) {
        aliasInput.sendKeys(alias);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        zipPostalCodeInput.sendKeys(zipPostalCode);
        countryInput.sendKeys(country);
        phoneInput.sendKeys(phone);

    }
    public void createAddress() {
        saveButton.click();
    }
}
