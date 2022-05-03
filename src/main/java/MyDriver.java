import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;

public class MyDriver {
    private ChromeOptions options;
    private ChromeDriver driver;
    private String chromeProfile = null;

    MyDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
        options = new ChromeOptions();
        try {
            options.addArguments(
                    "start-maximized",
                    "disable-infobars",
                    "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36",
                    "--disable-extensions",
                    "--disable-blink-features=AutomationControlled"
            );
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    MyDriver(String profileName) {
        chromeProfile = profileName;
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
        options = new ChromeOptions();
        try {
            options.addArguments(
                    "start-maximized",
                    "disable-infobars",
                    "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36",
                    "--disable-blink-features=AutomationControlled",
                    "--user-data-dir=C:/BrowserDrivers/User Data",
                    "--profile-directory=" + chromeProfile
            );
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChromeDriver getDriver() {
        return driver;
    }

    public ChromeOptions getOptions() {
        return options;
    }

    public String getChromeProfile() {
        return chromeProfile;
    }
}
