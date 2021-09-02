package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


import java.util.concurrent.TimeUnit;

import static Defaults.Utils.BASE_URL;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeAll
    protected static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    protected void startDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
     protected void tearDown(){
        driver.quit();
    }

}
