import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;


public class HelloSelenium {

    enum TypeToFind {
        CLASS,
        ID,
        NAME
    }


    static String pageLoadStatus = null;
    static JavascriptExecutor js;

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver(options);
//        driver.manage().window().minimize();


        driver.get("https://fakemail.adamwojdan.pl/");


        typeKeysLikeHuman(driver, TypeToFind.NAME, "name", "Twój Bank Internetowy");
//        sendKeysLikeHuman(driver, TypeToFind.NAME, "email", "info@twojbank.pl");
//        sendKeysLikeHuman(driver, TypeToFind.NAME, "toEmail", "wojdan.adam@gmail.com");
//        sendKeysLikeHuman(driver, TypeToFind.NAME, "subject", "Zmiana regulaminu");
//        sendKeysLikeHuman(driver, TypeToFind.NAME, "message", "Nowy regulamin jest pod tym linkiem: towcaleniefake.cc/1ryu9wdh1789tg89ighd19");

        Select dropdown = new Select(driver.findElement(By.id("typeOfContent")));
        dropdown.selectByIndex(1);

        randomDelayLikeHuman();

        driver.get("https://lesiogotuje.pl/");
        waitForPageLoad(driver);
    }


    public static void typeKeysLikeHuman(ChromeDriver driver, TypeToFind type, String fieldName, String textToWrite) throws InterruptedException {

        Random random = new Random();

        for (int i = 0; i < textToWrite.length(); i++) {
            switch (type) {
                case CLASS -> driver.findElement(By.className(fieldName)).sendKeys("" + textToWrite.charAt(i));
                case NAME -> driver.findElement(By.name(fieldName)).sendKeys("" + textToWrite.charAt(i));
                case ID -> driver.findElement(By.id(fieldName)).sendKeys("" + textToWrite.charAt(i));
            }

            String alphabet = "qwertyuiop[]asdfghjkl;'zxcvbnm,.1234567890-=";

            if (random.nextInt(0, 100) < 2) {

                String randomText = "" + alphabet.charAt(random.nextInt(0, alphabet.length()))
                        + alphabet.charAt(random.nextInt(0, alphabet.length()));

                switch (type) {
                    case CLASS -> driver.findElement(By.className(fieldName)).sendKeys(randomText);
                    case NAME -> driver.findElement(By.name(fieldName)).sendKeys(randomText);
                    case ID -> driver.findElement(By.id(fieldName)).sendKeys(randomText);
                }

                randomDelayLikeHuman();
                switch (type) {
                    case CLASS -> {
                        for (int j = 0; j < 2; j++) {
                            driver.findElement(By.className(fieldName)).sendKeys(Keys.BACK_SPACE);
                            Thread.sleep(50 + random.nextInt(-50, 50));
                        }
                    }
                    case NAME -> {
                        for (int j = 0; j < 2; j++) {
                            driver.findElement(By.name(fieldName)).sendKeys(Keys.BACK_SPACE);
                            Thread.sleep(50 + random.nextInt(-50, 50));
                        }
                    }
                    case ID -> {
                        for (int j = 0; j < 2; j++) {
                            driver.findElement(By.id(fieldName)).sendKeys(Keys.BACK_SPACE);
                            Thread.sleep(50 + random.nextInt(-50, 50));
                        }
                    }
                }
            }

            Thread.sleep(100 + random.nextInt(-95, 150));
            System.out.println("Random delay for type key: " + 100 + random.nextInt(-95, 150));
        }
        randomDelayLikeHuman();
    }

    public static void randomDelayLikeHuman() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(250, 1000));
    }

    public static void waitForPageLoad(ChromeDriver driver) throws InterruptedException {

        long currentTime = System.currentTimeMillis();
        do {
            js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));

        long pageLoadTime = System.currentTimeMillis() - currentTime;
        System.out.println("Page Loaded in " + pageLoadTime + " ms");

    }

}