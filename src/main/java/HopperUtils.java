import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HopperUtils {
    static final Map<String, String> ENV_VARS = System.getenv();
    static final Map<String, String> APPIUM_ENV = new HashMap<String, String>() {{
        put("APK_PATH", ENV_VARS.getOrDefault("HOPPER_APK_PATH", System.getProperty("user.dir")));
        put("APK_FILENAME", ENV_VARS.getOrDefault("HOPPER_APK_FILENAME", "apk/hopper_4.81.1-116270.apk"));
        put("APPIUM_URL", ENV_VARS.getOrDefault("HOPPER_APPIUM_URL", "http://localhost:4723/wd/hub"));
        put("APPIUM_PLATFORM_NAME", ENV_VARS.getOrDefault("HOPPER_PLATFORM_NAME", "Android"));
        put("APPIUM_PLATFORM_VERSION", ENV_VARS.getOrDefault("HOPPER_PLATFORM_VERSION", "9"));
        put("APPIUM_DEVICE_NAME", ENV_VARS.getOrDefault("HOPPER_DEVICE_NAME", "Android Emulator"));
        put("APPIUM_AUTOMATION_NAME", ENV_VARS.getOrDefault("HOPPER_AUTOMATION_NAME", "UiAutomator2"));
        put("APPIUM_AUTO_GRANT_PERMISSIONS", ENV_VARS.getOrDefault("HOPPER_AUTO_G   RANT_PERMISSIONS", "true"));
    }};

    private static AndroidDriver driver;
    private static WebDriverWait wait;

    public HopperUtils() throws MalformedURLException {
        System.out.println("### Appium URL: " + APPIUM_ENV.get("APPIUM_URL"));
        if(driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", APPIUM_ENV.get("APPIUM_PLATFORM_NAME"));
            desiredCapabilities.setCapability("platformVersion", APPIUM_ENV.get("APPIUM_PLATFORM_VERSION"));
            desiredCapabilities.setCapability("deviceName", APPIUM_ENV.get("APPIUM_DEVICE_NAME"));
            desiredCapabilities.setCapability("automationName", APPIUM_ENV.get("APPIUM_AUTOMATION_NAME"));
            desiredCapabilities.setCapability("app", APPIUM_ENV.get("APK_PATH") + "/" + APPIUM_ENV.get("APK_FILENAME"));
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, APPIUM_ENV.get("APPIUM_AUTO_GRANT_PERMISSIONS"));

            System.out.println("### Appium URL: " + APPIUM_ENV.get("APPIUM_URL"));

            driver = new AndroidDriver(new URL(APPIUM_ENV.get("APPIUM_URL")), desiredCapabilities);
            wait = new WebDriverWait(driver, 10);
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public MobileElement waitForElementById(String id) {
        return (MobileElement) wait.until( ExpectedConditions.presenceOfElementLocated( MobileBy.id( id)));
    }
}
