import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CookieManager {

    public static void save(ChromeDriver driver, File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            FileWriter fos = new FileWriter(file);
            BufferedWriter bos = new BufferedWriter(fos);

            for (Cookie ck : driver.manage().getCookies()) {
                bos.write((ck.getName() + ";"
                        + ck.getValue() + ";"
                        + ck.getDomain() + ";"
                        + ck.getPath() + ";"
                        + ck.getExpiry() + ";"
                        + ck.isSecure()));
                System.out.println(ck);
                bos.newLine();
            }
            bos.flush();
            bos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(ChromeDriver driver, File file) {
        try {
            if (file.exists()) {
                FileReader fis = new FileReader(file);
                BufferedReader bis = new BufferedReader(fis);
                driver.manage().deleteAllCookies();

                Scanner scanner = new Scanner(file);
                int lineCounter = 0;
                while (scanner.hasNextLine()) {
                    lineCounter++;
                    scanner.nextLine();
                }

                for (int i = 0; i < lineCounter; i++) {
                    System.out.println(i);
                    StringTokenizer tokenizer = new StringTokenizer(bis.readLine(), ";");
                    String name = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    String domain = tokenizer.nextToken();
                    String path = tokenizer.nextToken();

                    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                    String dateAsString = tokenizer.nextToken();
                    Date date;
                    if (dateAsString.equals("null")) {
                        date = null;
                    } else {
                        date = formatter.parse(dateAsString);
                    }
                    boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());

                    Cookie cookie = new Cookie.Builder(name, value)
                            .domain(domain)
                            .path(path)
                            .expiresOn(date)
                            .isSecure(isSecure)
                            .build();
                    driver.manage().addCookie(cookie);
                }
                bis.close();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
