package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SocialMediaZalando {
    private WebDriver driver;
    private WebDriverWait wait;

    private By instagramLink = By.xpath("//a[@data-tracking-label='social.2.Instagram']");
    private By facebookLink = By.xpath("//a[@data-tracking-label='social.1.Facebook']");

    public SocialMediaZalando(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void otvoriInstagram() throws InterruptedException {
        skrolajDoDna();
        WebElement insta = wait.until(ExpectedConditions.presenceOfElementLocated(instagramLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", insta);
        Thread.sleep(4000);
    }

    public void otvoriFacebook() throws InterruptedException {
        skrolajDoDna();
        WebElement fb = wait.until(ExpectedConditions.presenceOfElementLocated(facebookLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", fb);
        Thread.sleep(4000);
    }

    private void skrolajDoDna() throws InterruptedException {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
    }
}