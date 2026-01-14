# Zalando Automation Projekt

Ovaj projekt predstavlja automatizirani sustav za testiranje web trgovine **Zalando**.

Projekt je izrađen u razvojnom okruženju **IntelliJ IDEA** koristeći **Java** programski jezik, dok se upravljanje ovisnostima (dependencies) i pokretanje projekta vrši putem **Maven** alata.

## Arhitektura Projekta: Page Object Model (POM)

Projekt je razvijen koristeći **Page Object Model (POM)** dizajn. 

### 1. Pages (Elementi i Akcije)
U ovom paketu nalaze se klase koje predstavljaju specifične dijelove web stranice. Svaka klasa sadrži svoje lokatore (By) i metode za interakciju:

* **`ZalandoHomePage.java`**:
    * Služi za sve glavne interakcije na stranici (prihvaćanje kolačića, pretraga proizvoda, navigacija kroz kategorije).
    * Sadrži metode poput: `prihvatiKolacice()`, `pretrazi(String tekst)`, `otvoriKosaricu()`, `provjeriPraznuKosaricu()`, `klikniNaDjecu()`, `sortirajPoCijeni()`, `neuspjesnaPrijava(String tekst)`, `dodajUspješnoUKosaricu(String tekst)`, `idiUFooterIKlikni()`.

* **`SocialMediaZalando.java`**:
    * Posebna klasa za testiranje vanjskih poveznica u footeru (Instagram i Facebook).
    * Sadrži odvojene metode za otvaranje svake društvene mreže (`otvoriInstagram()` i `otvoriFacebook()`).
    * Implementira **JavaScriptExecutor** za precizno skrolanje do samog dna stranice.

### 2. Tests (Izvođenje i Validacija)
Ovdje se nalazi logika samih testova i njihovo pokretanje:

* **`BaseTest.java`**:
    * Sadrži osnovne postavke koje koriste svi testovi.
    * Definira `@BeforeMethod` (pokretanje Chromea, maksimiziranje prozora, postavljanje waitova) i `@AfterMethod` (gašenje sesije preglednika).

* **`ZalandoTests.java`**:
    * Glavna testna klasa koja sadrži 10 testova.
    * Svaki test poziva metode iz `Pages` klasa i koristi **Assert** naredbe za provjeru rezultata.
    * Implementira **Window Handling** (upravljanje tabovima) - bot otvara novi prozor, prebacuje se na njega, provjerava URL i vraća se na početni tab.

##  Napredne tehnike 

U projekt su ugrađene sljedeće dodatne tehnike:

1. **Page Object Model (POM)**: Cijeli projekt je strukturiran kroz odvojene klase.
2. **Wait naredbe (Explicit Wait)**: Umjesto fiksnih pauza, korišten je `WebDriverWait` uz `ExpectedConditions`. 
3. **WebDriverManager**: Implementiran `io.github.bonigarcia.webdrivermanager` koji automatski upravlja verzijama Chrome drivera, eliminirajući potrebu za ručnim postavljanjem putanja.
4. **.gitignore**: Dodan je .gitignore koji sprječava slanje nepotrebnih `target/` i `.idea/` datoteka.
5. **README.md**: Kreirana je detaljna dokumentacija projekta.
6. **OOP (Objektno orijentirano programiranje)**:
    - **Nasljeđivanje**: Svi testovi nasljeđuju `BaseTest`.
    - **Enkapsulacija**: Lokatori su privatni (`private By`), a interakcija se vrši kroz javne metode.
7. **Window Handling**: Implementirano napredno upravljanje tabovima. Testovi otvaraju nove kartice (Instagram/Facebook), prebacuju fokus, potvrđuju URL i sigurno se vraćaju na početni tab.
8. **Reporting**: Korišten je konzolni ispis koraka u realnom vremenu za lakše praćenje izvršavanja.

##  Popis Testova
1.  **Test otvaranja Zalando stranice preko Google.**
2.  **Test pretrage "Nike" u searchbar-u.**
3.  **Test ulaska u košaricu i provjere jel prazna.**
4.  **Test klikanja na gumb "Djeca" kako bi se otvorila kateogrija dječjih proizvoda.**
5.  **Test sortiranja proizvoda po cijeni od najniže.**
6.  **Test unosa neispravnog e-mail, test prolazi ako prijava nije usješna.**
7.  **Test pretraživanja proizvoda "Nike majica", klikanje na prvi proizvod, odabir XS veličine te dodavanje u košaricu.**
8.  **Test odlaska na dno stranice te klikanje "Informacije o povratu novca".**
9.  **Test odlaska na dno stranice, odabir Instagram ikone te otvaranje Instagram profila u novom tabu.**
10. **Test odlaska na dno stranice, odabir Facebook ikone te otvaranje Facebook profila u novom tabu.**

##  Kako pokrenuti projekt
1. Klonirajte projekt.
2. Otvorite projekt u **IntelliJ IDEA**.
3. Pričekajte da Maven preuzme ovisnosti iz `pom.xml`.
4. Desni klik na `src/test/java/tests/ZalandoTests.java` -> **Run**.
