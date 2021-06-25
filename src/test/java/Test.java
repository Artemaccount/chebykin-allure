import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Test {
    private static String pathScreenshots = "./screenshots/";
    private static String filename = "screenshot";

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File currentScreenshot = new File(pathScreenshots + filename + ".png");
        ImageIO.write(screenshot.getImage(), "png", currentScreenshot);
        return Files.toByteArray(currentScreenshot);
    }

    @Step("Открываем мемасик")
    @org.junit.jupiter.api.Test
    void openMem() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String expected = "https://cs.pikabu.ru/images/big_size_comm/2013-10_1/13805822604058.jpg";
        driver.get(expected);
        driver.manage().window().maximize();
        saveScreenshot(driver);
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Step("Этап вступительный")
    @org.junit.jupiter.api.Test
    void first_test() {
        Assertions.assertEquals(1, 1);
    }

    @Step("Этап заключительный")
    @org.junit.jupiter.api.Test
    void last_step() {
        Assertions.assertNotEquals(4, 5);
    }
}
