package MakeMyTrip;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MakeMyTripTestNG {

    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.makemytrip.global");
    }

    @Test
    public void flightSearchTest() throws InterruptedException {

        Thread.sleep(4000);

        WebElement IntroPopUpCloseButton =
                driver.findElement(By.xpath("//span[@class='commonModal__close']"));
        IntroPopUpCloseButton.click();

        WebElement FromBox =
                driver.findElement(By.xpath("//label[@for='fromCity']"));
        FromBox.click();

        Thread.sleep(3000);

        WebElement FromSearchTextBox =
                driver.findElement(By.xpath("//input[@placeholder='From']"));
        FromSearchTextBox.sendKeys("HYD");

        Thread.sleep(5000);

        WebElement FromTopSearchResult =
                driver.findElement(By.xpath("//p[normalize-space()='Rajiv Gandhi International Airport']"));
        Thread.sleep(3000);
        FromTopSearchResult.click();

        Thread.sleep(3000);

        WebElement toBox =
                driver.findElement(By.xpath("//label[@for='toCity']"));
        toBox.click();

        WebElement toSearchTextBox =
                driver.findElement(By.xpath("//input[@placeholder='To']"));
        toSearchTextBox.sendKeys("MAA");

        Thread.sleep(4000);

        WebElement toTopSearchResult =
                driver.findElement(By.xpath("//p[normalize-space()='Chennai International Airport']"));
        Thread.sleep(3000);
        toTopSearchResult.click();

        WebElement departure =
                driver.findElement(By.xpath("//label[@for='departure']"));
        departure.click();

        Thread.sleep(3000);

        WebElement departureCalenderCell =
                driver.findElement(By.xpath("//div[@aria-label='Wed Mar 04 2026']//div[@class='dateInnerCell']"));
        departureCalenderCell.click();

        Thread.sleep(3000);

        WebElement returnTile =
                driver.findElement(By.xpath("//div[@data-cy='returnArea']//label"));
        returnTile.click();

        Thread.sleep(5000);

        WebElement returnCalenderCell =
                driver.findElement(By.xpath("//div[@aria-label='Fri Mar 06 2026']//div[@class='dateInnerCell']"));
        returnCalenderCell.click();

        Thread.sleep(3000);

        WebElement searchButton =
                driver.findElement(By.xpath("//a[normalize-space()='Search']"));
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'listingCard')]")));

        Thread.sleep(3000);

        String expectedSearchResultsPageTitle = "MakeMyTrip";
        String searchResultsPageTitle = driver.getTitle();

        System.out.println("The page title = " + searchResultsPageTitle);

        if (expectedSearchResultsPageTitle.equals(searchResultsPageTitle)) {
            System.out.println("The search results page was loaded properly");
        } else {
            System.out.println("ERROR: The search results page was not loaded properly");
        }
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}