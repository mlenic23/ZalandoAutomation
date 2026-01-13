package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SocialMediaZalando;
import pages.ZalandoHomePage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class ZalandoTests extends BaseTest {

    @Test(priority = 1)
    public void testOtvaranjaPrekoGooglea() throws InterruptedException {
        driver.get("https://www.google.com");

        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement prihvatiSve = localWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Prihvati sve'] | //button//div[text()='Prihvati sve']")));
            prihvatiSve.click();
        } catch (Exception e) {
            System.out.println("Google kolačići se nisu pojavili.");
        }

        WebElement googleSearch = localWait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        googleSearch.sendKeys("Zalando HR");
        googleSearch.sendKeys(Keys.ENTER);

        WebElement glavniLink = localWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'zalando.hr') and .//h3] | //h3[contains(text(), 'Zalando')]")));

        glavniLink.click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("zalando"), "Nismo na Zalando stranici!");
        System.out.println("Uspješno ušli na Zalando preko Googlea!");
    }

    @Test(priority = 2)
    public void testPretrageProizvoda() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        home.pretrazi("Nike");

        Thread.sleep(3000);

        boolean imaProizvoda = driver.findElements(By.xpath("//article")).size() > 0;

        System.out.println("Pronađeni proizvodi: " + (imaProizvoda ? "DA" : "NE"));

        Assert.assertTrue(imaProizvoda, "Test pao! Stranica s rezultatima je prazna.");
    }

    @Test(priority = 3)
    public void testPrazneKosaricePrekoGumba() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        home.provjeriPraznuKosaricu();

        boolean prazno = driver.getPageSource().contains("prazna");
        Assert.assertTrue(prazno, "Košarica bi trebala biti prazna, ali poruka nije pronađena!");
        System.out.println("Test prazne košarice uspješan!");
    }

    @Test(priority = 4)
    public void testNavigacijaDjeca() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();
        home.klikniNaDjecu();
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("djeca"), "Gumb 'Djeca' nas nije odveo na pravi odjel!");
    }

    @Test(priority = 5)
    public void testSortiranja() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        home.sortirajPoCijeni();

        Thread.sleep(4000);

        String trenutniUrl = driver.getCurrentUrl();
        Thread.sleep(4000);
        System.out.println("URL nakon sortiranja: " + trenutniUrl);

        boolean sortirano = trenutniUrl.contains("order") || trenutniUrl.contains("price");
        Thread.sleep(4000);
        Assert.assertTrue(sortirano, "Sortiranje nije primijenjeno! URL: " + trenutniUrl);
    }

    @Test(priority = 6)
    public void testNeuspjesnaPrijava() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        home.neuspjesnaPrijava("marta.test.2026@gmail.com");

        Thread.sleep(2000);
        String stranica = driver.getPageSource();
        boolean greskaPrikazana = stranica.contains("ispravnu") || stranica.contains("provjerite") || stranica.contains("Lozinka");

        Assert.assertTrue(greskaPrikazana, "Sustav nije prikazao grešku za nepostojeći mail!");
    }

    @Test(priority = 7)
    public void testDodavanjaUKosaricu() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        System.out.println("Započinjem dodavanje proizvoda u košaricu...");
        home.prihvatiKolacice();
        home.dodajUspjesnoUKosaricu("Nike majica");

        home.otvoriKosaricu();
        Thread.sleep(3000);

        String izvor = driver.getPageSource();
        boolean kosaricaNijePrazna = !izvor.contains("Tvoja je košarica prazna");

        Assert.assertTrue(kosaricaNijePrazna, "Proizvod se nije pojavio u košarici!");
        System.out.println("Test uspješan: Proizvod je u košarici!");
    }

    @Test(priority = 8)
    public void testFooterInformacije() throws InterruptedException {
        ZalandoHomePage home = new ZalandoHomePage(driver);
        home.prihvatiKolacice();

        home.idiUFooterIKlikni();

        String trenutniUrl = driver.getCurrentUrl();
        Assert.assertTrue(trenutniUrl.contains("faq") || trenutniUrl.contains("help"),
                "Stranica s informacijama o povratu se nije otvorila!");

    }

    @Test(priority = 9)
    public void testInstagramNavigacije() throws InterruptedException {
        SocialMediaZalando social = new SocialMediaZalando(driver);
        String glavniTab = driver.getWindowHandle();

        social.otvoriInstagram();
        Thread.sleep(5000);

        Set<String> sviTabovi = driver.getWindowHandles();
        for (String tab : sviTabovi) {
            if (!tab.equals(glavniTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        System.out.println("Instagram URL: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("instagram"), "Fejlao Instagram!");

        driver.switchTo().window(glavniTab);
    }

    @Test(priority = 10)
    public void testFacebookNavigacije() throws InterruptedException {
        SocialMediaZalando social = new SocialMediaZalando(driver);
        String glavniTab = driver.getWindowHandle();

        social.otvoriFacebook();
        Thread.sleep(5000);

        Set<String> sviTabovi = driver.getWindowHandles();
        for (String tab : sviTabovi) {
            if (!tab.equals(glavniTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        System.out.println("Facebook URL: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"), "Fejlao Facebook!");

        driver.switchTo().window(glavniTab);
    }

}




