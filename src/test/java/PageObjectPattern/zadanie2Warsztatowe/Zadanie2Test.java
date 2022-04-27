package PageObjectPattern.zadanie2Warsztatowe;

import PageObjectPattern.myStorePage.AuthenticationPage;
import PageObjectPattern.myStorePage.CheckHummingbirdSweaterSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.apache.commons.io.FileUtils; //obiekt pliku kopiuje i zapisuje


import java.io.File;
import java.io.IOException;

public class Zadanie2Test {

    private WebDriver driver;

    @Test
    public void userIsBoughtHummingbirdPrintedSweater() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.logIn("alfonskawka88@email.com", "Admin1234");

        WebElement clothesButton = driver.findElement(By.xpath("//*[@id=\"category-3\"]/a"));
        clothesButton.click();

        WebElement hummingbirdPrintedSweaterTile = driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/article[2]/div/a/img"));
        hummingbirdPrintedSweaterTile.click();

        WebElement specialOffer = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[2]"));
        System.out.println(specialOffer.getText());
        Assertions.assertEquals(specialOffer.getText(), "SAVE 20%");

        CheckHummingbirdSweaterSize checkHummingbirdSweaterSize = new CheckHummingbirdSweaterSize(driver);
        checkHummingbirdSweaterSize.sizeM("M");

        driver.findElement(By.cssSelector(".touchspin-up")).click();
        driver.findElement(By.cssSelector(".touchspin-up")).click();

        {
            WebElement quantity = driver.findElement(By.cssSelector(".touchspin-up"));
            Actions builder = new Actions(driver);
            builder.doubleClick(quantity).perform();
        }
        driver.findElement(By.cssSelector(".touchspin-up")).click();
        driver.findElement(By.cssSelector(".touchspin-up")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".cart-content-btn > .btn-primary")).click();

        WebElement checkoutButton = driver.findElement(By.cssSelector("#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div.card.cart-summary > div.checkout.cart-detailed-actions.card-block > div > a"));
        checkoutButton.click();

        WebElement aliasAddressFirst = driver.findElement(By.cssSelector(".address-alias"));
        System.out.println(aliasAddressFirst.getText());
        Assertions.assertEquals(aliasAddressFirst.getText(), "alfons1");

        driver.findElement(By.name("confirm-addresses")).click();
        driver.findElement(By.cssSelector("#js-delivery > div > div.delivery-options > div:nth-child(1) > div > span > span")).click();
        driver.findElement(By.cssSelector("#js-delivery > button")).click();

        driver.findElement(By.cssSelector("#payment-option-1")).click();
        driver.findElement(By.cssSelector("#conditions_to_approve\\[terms-and-conditions\\]")).click();
        driver.findElement(By.cssSelector("#payment-confirmation > div.ps-shown-by-js > button")).click();

        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(".\\Screenshots\\Mads_Cruz_screenshot.png"));

    }

}




