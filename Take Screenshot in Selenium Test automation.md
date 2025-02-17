# Create BIdireaction connection with driver first

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class Main {

  static protected WebDriver driver;

  static {
    chromedriver().setup();

    var options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    //options.enableBiDi(); //needed for screenshots
    driver = new ChromeDriver(options);
  }

  public static void main(String[] args) {
    driver.get("https://google.com");
    driver.quit();
  }
}


```


## Now Take Screenshots

```java
package browser_interactions;

import setup.Main;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.remote.RemoteWebElement;

public class Screenshots extends Main {

  public static void main(String[] args) {
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");

    var browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

    // FULL PAGE
    String fullScreenshot = browsingContext.captureScreenshot();
    saveScreenshot(fullScreenshot, "full_screenshot.png");

    // ELEMENT
    WebElement colorPicker = driver.findElement(By.name("my-colors"));
    String internalElementId = ((RemoteWebElement) colorPicker).getId();
    String elementScreenshot = browsingContext.captureElementScreenshot(internalElementId);
    saveScreenshot(elementScreenshot, "element_screenshot.png");

    // VIEWPORT
    driver.findElement(By.name("my-date")).click();
    var datePicker = driver.findElement(By.className("datepicker")).getRect();
    String viewportScreenshot = browsingContext.captureBoxScreenshot(
        datePicker.getX(),
        datePicker.getY(),
        datePicker.getWidth(),
        datePicker.getHeight());
    saveScreenshot(viewportScreenshot, "viewport_screenshot.png");

    driver.quit();
  }

  private static void saveScreenshot(String screenshot, String filename) {
    var decodedScreenshot = Base64.getDecoder().decode(screenshot);
    try {
      String path = "/workspaces/test-automation-with-selenium-webdriver-for-java-2124033/screenshots/";
      Files.write(Paths.get(path + filename), decodedScreenshot);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

```
