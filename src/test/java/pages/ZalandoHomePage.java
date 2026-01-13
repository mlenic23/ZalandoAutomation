package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ZalandoHomePage {
    WebDriver driver;
    WebDriverWait wait;

    private By acceptCookies = By.xpath("//button[contains(@id, 'sp-cc-accept')] | //button[contains(., 'Sve u redu')] | //button[contains(., 'Prihvaćam')]");
    private By searchBox = By.xpath("//input[@name='q'] | //input[@type='search']");
    private By kidsSection = By.xpath("//a[contains(@href, '/djeca-home')]");
    private By emailInput = By.xpath("//input[@id='login.email'] | //input[@type='email'] | //input[@name='login.email']");
    private By continueButton = By.xpath("//button[@type='submit'] | //button[contains(.,'Nastavi')] | //button[contains(.,'Prijavi se')]");
    private By prviProizvod = By.xpath("//article//a | //div[@data-testid='product-card']//a");
    private By gumbVelicina = By.xpath("//span[contains(text(), 'Odaberi') and contains(text(), 'veličinu')] | //button[@id='picker-trigger']");
    private By velicinaXS = By.xpath("//span[text()='XS'] | //div[text()='XS'] | //label[contains(., 'XS')]");
    private By gumbDodajUKosaricu = By.xpath("//button[contains(@aria-label, 'Dodaj u košaricu')] | //span[text()='Dodaj u košaricu']/..");
    private By ikonaProfila = By.xpath("//button[@data-testid='user-account'] | //span[contains(., 'Tvoj račun')]");
    private By gumbUdiUPrijavu = By.xpath("//div[contains(@class, 'Wy3rmK')]//button | //a[contains(@href, '/login')]");
    private By gumbKosarica = By.xpath("//a[@data-testid='cart-link']"); 
    private By footerLinkPovrat = By.xpath("//span[contains(text(), 'Informacije o povratu novca')]");

    public ZalandoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void prihvatiKolacice() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
        } catch (Exception e) {
            System.out.println("Kolačići se nisu pojavili.");
        }
    }

    public void pretrazi(String tekst) throws InterruptedException {
        WebElement polje = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        polje.clear();
        polje.sendKeys(tekst);
        Thread.sleep(1000);
        polje.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    public void otvoriKosaricu() {
        driver.get("https://www.zalando.hr/cart/");
    }

    public void provjeriPraznuKosaricu() throws InterruptedException {
        System.out.println("Klikam na gumb košarice...");
        WebElement kosarica = wait.until(ExpectedConditions.elementToBeClickable(gumbKosarica));
        try {
            kosarica.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", kosarica);
        }

        Thread.sleep(3000);
    }

    public void klikniNaDjecu() {
        wait.until(ExpectedConditions.elementToBeClickable(kidsSection)).click();
    }

    public void sortirajPoCijeni() {
        driver.get("https://www.zalando.hr/katalog/?order=price_asc&q=tenisice");
    }

    public void neuspjesnaPrijava(String kriviEmail) throws InterruptedException {
        WebElement profil = wait.until(ExpectedConditions.visibilityOfElementLocated(ikonaProfila));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(profil).perform();
        Thread.sleep(2000);

        WebElement gumbPrijava = wait.until(ExpectedConditions.presenceOfElementLocated(gumbUdiUPrijavu));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", gumbPrijava);
        System.out.println("Kliknula na gumb Prijava...");
        Thread.sleep(4000);

        WebElement polje = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        polje.clear();
        polje.sendKeys(kriviEmail);
        Thread.sleep(1000);

        WebElement nastavi = driver.findElement(continueButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", nastavi);
        Thread.sleep(3000);
    }

    public void dodajUspjesnoUKosaricu(String pojam) throws InterruptedException {
        pretrazi(pojam);
        Thread.sleep(4000);

        WebElement proizvod = wait.until(ExpectedConditions.presenceOfElementLocated(prviProizvod));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", proizvod);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", proizvod);
        System.out.println("Otvorila proizvod...");
        Thread.sleep(5000);

        WebElement gumb = wait.until(ExpectedConditions.presenceOfElementLocated(gumbVelicina));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", gumb);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", gumb);
        System.out.println("Kliknula na izbornik veličina...");
        Thread.sleep(2000);

        WebElement odabranaVelicina = wait.until(ExpectedConditions.presenceOfElementLocated(velicinaXS));
        js.executeScript("arguments[0].click();", odabranaVelicina);
        System.out.println("Odabrala XS.");
        Thread.sleep(2000);

        WebElement dodajGumb = wait.until(ExpectedConditions.presenceOfElementLocated(gumbDodajUKosaricu));
        js.executeScript("arguments[0].click();", dodajGumb);
        System.out.println("Uspješno dodano u košaricu!");
        Thread.sleep(3000);
    }

    public void idiUFooterIKlikni() throws InterruptedException {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(footerLinkPovrat));
        link.click();
        Thread.sleep(3000);
    }

}



