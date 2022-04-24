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

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); //This is the location where you have installed Firefox on your machine
//        System.setProperty("webdriver.gecko.driver", "C:\\BrowserDrivers\\geckodriver.exe");
//        FirefoxDriver driver = new FirefoxDriver(options);
//        FirefoxDriver driver = new FirefoxDriver();

        ChromeDriver driver = new ChromeDriver(options);
//        driver.manage().window().minimize();


        driver.get("https://fakemail.adamwojdan.pl/");


        sendKeysLikeHuman(driver, TypeToFind.NAME, "name", "Twój Bank Internetowy");
        randomDelayLikeHuman();

        sendKeysLikeHuman(driver, TypeToFind.NAME, "email", "info@twojbank.pl");
        randomDelayLikeHuman();

        sendKeysLikeHuman(driver, TypeToFind.NAME, "toEmail", "wojdan.adam@gmail.com");
        randomDelayLikeHuman();

        sendKeysLikeHuman(driver, TypeToFind.NAME, "subject", "Zmiana regulaminu");
        randomDelayLikeHuman();

        sendKeysLikeHuman(driver, TypeToFind.NAME, "message", "Nowy regulamin jest pod tym linkiem: towcaleniefake.cc/1ryu9wdh1789tg89ighd19");
        randomDelayLikeHuman();

        Select dropdown = new Select(driver.findElement(By.id("typeOfContent")));
        dropdown.selectByIndex(1);

        randomDelayLikeHuman();
        randomDelayLikeHuman();


        driver.get("https://projekty.adamwojdan.pl");
        waitForPageLoad(driver);


//        driver.get("https://adamwojdan.pl/wp-login.php?redirect_to=https%3A%2F%2Fadamwojdan.pl%2Fwp-admin%2F&reauth=1");
//        Thread.sleep(500);
//        driver.findElement(By.name("name")).sendKeys("PKO Bank Polski");
//        Thread.sleep(500);
//        driver.findElement(By.name("email")).sendKeys("info@ipko.pl");
//        Thread.sleep(500);
//        WebElement recaptcha = driver.findElement(By.id("wp-submit"));
//        recaptcha.click();

//        Thread.sleep(2500);
//
//        driver.get("https://adamwojdan.pl/wp-admin/post-new.php?post_type=page");

    }


    public static void sendKeysLikeHuman(ChromeDriver driver, TypeToFind type, String fieldName, String textToWrite) throws InterruptedException {

        Random random = new Random();

        for (int i = 0; i < textToWrite.length(); i++) {
            switch (type) {
                case CLASS -> driver.findElement(By.className(fieldName)).sendKeys("" + textToWrite.charAt(i));
                case NAME -> driver.findElement(By.name(fieldName)).sendKeys("" + textToWrite.charAt(i));
                case ID -> driver.findElement(By.id(fieldName)).sendKeys("" + textToWrite.charAt(i));
            }

            String alphabet = "qwertyuiop[]asdfghjkl;'zxcvbnm,.1234567890-=";

            if (random.nextInt(0,100) < 10) {

                String randomText = "" + alphabet.charAt(random.nextInt(0,alphabet.length()))
                        + alphabet.charAt(random.nextInt(0,alphabet.length()));

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
            System.out.println(100 + random.nextInt(-95, 150));
        }
    }

    public static void randomDelayLikeHuman() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(500 + random.nextInt(-250, 750));
    }


    public static void waitForPageLoad(ChromeDriver driver) {

        do {
            js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));

        System.out.println("Page Loaded.");

    }

}