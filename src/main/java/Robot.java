import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Robot {

    public static void main(String[] args) throws InterruptedException {

        MyDriver hiddenChrome = new MyDriver();
        ChromeDriver driver = hiddenChrome.getDriver();

        File file = new File("cookies.data");

//        if (hiddenChrome.getChromeProfile() == null) {
//            if (file.exists()) {
//                getAndWaitForPageLoad(driver, "https://adamwojdan.pl/centrumzarzadzaniakosmosem/");
//                CookieManager.load(driver, file);
//                getAndWaitForPageLoad(driver, "https://adamwojdan.pl/centrumzarzadzaniakosmosem/");
//            } else {

        // ADAMWOJDAN.PL
//                getAndWaitForPageLoad(driver, "https://adamwojdan.pl/recepreczodtejstrony");
//                randomDelayLikeHuman();
//                typeString(driver, FindElementBy.ID, "user_login", "test1234", true);
//                typeString(driver, FindElementBy.ID, "user_pass", "Hwt5%S%sGEkLlqTYpkx8XEBX", true);
//
//                clickToWebElement(driver, FindElementBy.ID, "rememberme");
//                clickToWebElement(driver, FindElementBy.ID, "wp-submit");
//                waitForPageLoad(driver);


//            }
//        }

        // BASELINKER
        getAndWaitForPageLoad(driver, "https://login.baselinker.com/");
        randomDelayLikeHuman();
        typeString(driver, FindElementBy.ID, "loginField", "rrem0h@gmail.com", false);
        typeString(driver, FindElementBy.ID, "passwordField", "Qazwsx886741", false);

        clickToWebElement(driver, FindElementBy.ID, "signinButton");
        waitForPageLoad(driver);


        randomDelayLikeHuman(3000, 4000);
        getAndWaitForPageLoad(driver, "https://panel-d.baselinker.com/orders_macros.php");
        randomDelayLikeHuman(1000, 2000);

        Countries.init();


        try {
            for (int i = 0; i < Countries.countriesId.size(); i++) {

                WebElement copyActionButton = driver.findElement(By.cssSelector(".btn_macro_copy.fa.fa-copy.fa-lg"));
                copyActionButton.click();

                randomDelayLikeHuman(2000+ (i*10), 3000 + i*(10));

                WebElement typeCountry = driver.findElement(By.cssSelector(".select2-search__field"));
                randomDelayLikeHuman(50, 100);

                typeCountry.sendKeys(Keys.BACK_SPACE);
                typeCountry.clear();
                typeCountry.sendKeys(Countries.countries.get(i));
                typeCountry.sendKeys(Keys.ENTER);
                randomDelayLikeHuman(250, 500);

                WebElement typeAdditionalCondition = driver.findElement(By.xpath("//textarea[@placeholder='Zawartość pola dodatkowego']"));
                typeAdditionalCondition.clear();
                typeAdditionalCondition.sendKeys(Countries.countriesId.get(i) + "*[Faktura]");
                randomDelayLikeHuman(250, 500);

                WebElement saveButton = driver.findElement(By.id("btnSaveEdition"));
                saveButton.click();
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("window.scrollBy(0,-10000)");
                randomDelayLikeHuman(1000, 1500);

            }
        } catch (Exception e) {
            e.printStackTrace();
//            driver.get("https://panel-d.baselinker.com/logout.php");
        }


// FAKEMAIL
//        getAndWaitForPageLoad(driver, "https://fakemail.adamwojdan.pl/");
//        typeString(driver, FindElementBy.NAME, "name", "Nazwa", true);
//        typeString(driver, FindElementBy.NAME, "email", "kontakt@adamwojdan.pl", true);
//        typeString(driver, FindElementBy.NAME, "toEmail", "wojdan.adam@gmail.com", true);
//        typeString(driver, FindElementBy.NAME, "subject", "Dzień dobry!", true);
//        typeString(driver, FindElementBy.NAME, "message", "Hola Amigo!", true);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
//        System.out.println("Clicked the checkbox");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Save Cookies? [Y/N]: ");

        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            CookieManager.save(driver, file);
        }

        System.out.println("Exit Chrome? [Y/N]: ");
        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            driver.quit();
        }
    }

    private static void getAndWaitForPageLoad(ChromeDriver driver, String url) throws InterruptedException {
        driver.get(url);
        waitForPageLoad(driver);
        randomDelayLikeHuman();
    }

    private static void selectDropdown(ChromeDriver driver, FindElementBy type, String fieldName, String textToWrite) {
    }

    private static void clickToWebElement(ChromeDriver driver, FindElementBy type, String elementName) throws InterruptedException {
        WebElement webElement;
        switch (type) {
            case CLASS_NAME -> webElement = driver.findElement(By.className(elementName));
            case CSS_SELECTOR -> webElement = driver.findElement(By.cssSelector(elementName));
            case ID -> webElement = driver.findElement(By.id(elementName));
            case NAME -> webElement = driver.findElement(By.name(elementName));
            case LINK_TEXT -> webElement = driver.findElement(By.linkText(elementName));
            case PARTIAL_LINK_TEXT -> webElement = driver.findElement(By.partialLinkText(elementName));
            case TAG_NAME -> webElement = driver.findElement(By.tagName(elementName));
            case X_PATH -> webElement = driver.findElement(By.xpath(elementName));
            default -> webElement = driver.findElement(By.id(elementName));
        }
        webElement.click();
        randomDelayLikeHuman(10, 250);
    }


    public static void typeString(ChromeDriver driver, FindElementBy type, String elementName, String textToWrite, boolean letterByLetter) throws InterruptedException {

        Random random = new Random();

        WebElement webElement;

        if (letterByLetter) {
            for (int i = 0; i < textToWrite.length(); i++) {
                switch (type) {
                    case CLASS_NAME -> {
                        webElement = driver.findElement(By.className(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }

                    case CSS_SELECTOR -> {
                        webElement = driver.findElement(By.cssSelector(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case ID -> {
                        webElement = driver.findElement(By.id(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case NAME -> {
                        webElement = driver.findElement(By.name(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case LINK_TEXT -> {
                        webElement = driver.findElement(By.linkText(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case PARTIAL_LINK_TEXT -> {
                        webElement = driver.findElement(By.partialLinkText(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case TAG_NAME -> {
                        webElement = driver.findElement(By.tagName(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                    case X_PATH -> {
                        webElement = driver.findElement(By.xpath(elementName));
                        webElement.sendKeys("" + textToWrite.charAt(i));
                    }
                }
                randomDelayLikeHuman(100, 250);
            }
        } else {
            switch (type) {
                case CLASS_NAME -> driver.findElement(By.className(elementName)).sendKeys("" + textToWrite);
                case CSS_SELECTOR -> driver.findElement(By.cssSelector(elementName)).sendKeys("" + textToWrite);
                case ID -> driver.findElement(By.id(elementName)).sendKeys("" + textToWrite);
                case NAME -> driver.findElement(By.name(elementName)).sendKeys("" + textToWrite);
                case LINK_TEXT -> driver.findElement(By.linkText(elementName)).sendKeys("" + textToWrite);
                case PARTIAL_LINK_TEXT -> driver.findElement(By.partialLinkText(elementName)).sendKeys("" + textToWrite);
                case TAG_NAME -> driver.findElement(By.tagName(elementName)).sendKeys("" + textToWrite);
                case X_PATH -> driver.findElement(By.xpath(elementName)).sendKeys("" + textToWrite);
            }
        }
        randomDelayLikeHuman();
    }

    public static void randomDelayLikeHuman() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(500, 1000);
        System.out.println(randomInt);
        Thread.sleep(randomInt);
    }

    public static void randomDelayLikeHuman(int origin, int bound) throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(origin, bound);
        System.out.println(randomInt);
        Thread.sleep(randomInt);
    }


    public static void waitForPageLoad(ChromeDriver driver) throws InterruptedException {

        long currentTime = System.currentTimeMillis();
        JavascriptExecutor js;
        String pageLoadStatus;
        do {
            js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));

        long pageLoadTime = System.currentTimeMillis() - currentTime;
        System.out.println("Page Loaded in " + pageLoadTime + " ms");

    }

}