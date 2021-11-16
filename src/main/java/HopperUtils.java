import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Utils and tools that are shared by all test files, example to get the Appium driver or to wait for an element.
 */
public class HopperUtils {
    static final Map<String, String> ENV_VARS = System.getenv();

    /**
     * Default values is to run on the local of a developer with the Android Studio Emulator and the Appium Server GUI.
     * Using Environment variables will override the default values:
     *   HOPPER_PLATFORM_NAME=Android
     *   HOPPER_DEVICE_NAME=Google Pixel 3 GoogleAPI Emulator
     *   HOPPER_PLATFORM_VERSION=9
     *   HOPPER_APK_FILENAME=https://github.com/AlainBouchard/appium/raw/master/apk/hopper_4.81.1-116270.apk
     *   HOPPER_APPIUM_URL=<SAUCELABS_URL>
     *   HOPPER_AUTO_GRANT_PERMISSIONS=true
     *   HOPPER_SAUCELABS_APPIUM_VERSION=1.20.2
     *   HOPPER_SAUCELABS_USERNAME=<SAUCELABS_USERNAME>
     *   HOPPER_SAUCELABS_KEY=<SAUCELABS_ACCESSKEY>
     */
    static final Map<String, String> APPIUM_ENV = new HashMap<String, String>() {{
        put("APK_FILENAME", ENV_VARS.getOrDefault("HOPPER_APK_FILENAME", System.getProperty("user.dir") + "/apk/hopper_4.81.1-116270.apk"));
        put("APPIUM_URL", ENV_VARS.getOrDefault("HOPPER_APPIUM_URL", "http://localhost:4723/wd/hub"));
        put("APPIUM_PLATFORM_NAME", ENV_VARS.getOrDefault("HOPPER_PLATFORM_NAME", "Android"));
        put("APPIUM_PLATFORM_VERSION", ENV_VARS.getOrDefault("HOPPER_PLATFORM_VERSION", "9"));
        put("APPIUM_DEVICE_NAME", ENV_VARS.getOrDefault("HOPPER_DEVICE_NAME", "Android Emulator"));
        put("APPIUM_AUTOMATION_NAME", ENV_VARS.getOrDefault("HOPPER_AUTOMATION_NAME", "UiAutomator2"));
        put("APPIUM_AUTO_GRANT_PERMISSIONS", ENV_VARS.getOrDefault("HOPPER_AUTO_GRANT_PERMISSIONS", "true"));

        put("SAUCELABS_APPIUM_VERSION", ENV_VARS.getOrDefault("HOPPER_SAUCELABS_APPIUM_VERSION", null));
        put("SAUCELABS_USERNAME", ENV_VARS.getOrDefault("HOPPER_SAUCELABS_USERNAME", null));
        put("SAUCELABS_KEY", ENV_VARS.getOrDefault("HOPPER_SAUCELABS_KEY", null));
    }};

    private static AndroidDriver driver;
    private static WebDriverWait wait;

    public HopperUtils() throws MalformedURLException {
        if(driver == null) {
            DesiredCapabilities desiredCapabilities = getDesiredCapabilities();

            driver = new AndroidDriver(new URL(APPIUM_ENV.get("APPIUM_URL")), desiredCapabilities);
            wait = new WebDriverWait(driver, 10);
        }
    }

    /**
     * Configure Appium capabilities from default values or the Environment Variables.
     * @return DesiredCapabilities
     */
    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", APPIUM_ENV.get("APPIUM_PLATFORM_NAME"));
        desiredCapabilities.setCapability("platformVersion", APPIUM_ENV.get("APPIUM_PLATFORM_VERSION"));
        desiredCapabilities.setCapability("deviceName", APPIUM_ENV.get("APPIUM_DEVICE_NAME"));
        desiredCapabilities.setCapability("automationName", APPIUM_ENV.get("APPIUM_AUTOMATION_NAME"));
        desiredCapabilities.setCapability("app", APPIUM_ENV.get("APK_FILENAME"));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, APPIUM_ENV.get("APPIUM_AUTO_GRANT_PERMISSIONS"));

        if(APPIUM_ENV.get("SAUCELABS_APPIUM_VERSION") != null) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion", APPIUM_ENV.get("SAUCELABS_APPIUM_VERSION"));
            desiredCapabilities.setCapability("sauce:options", sauceOptions);
        }

        if(APPIUM_ENV.get("SAUCELABS_USERNAME") != null) {
            desiredCapabilities.setCapability("username", APPIUM_ENV.get("SAUCELABS_USERNAME"));
        }

        if(APPIUM_ENV.get("SAUCELABS_KEY") != null) {
            desiredCapabilities.setCapability("accessKey", APPIUM_ENV.get("SAUCELABS_KEY"));
        }

        // TODO: Improve by adding a Logger (tracking: JIRA-####)
        System.out.println("### Appium URL: " + APPIUM_ENV.get("APPIUM_URL"));
        System.out.println("### SauceLabs Username: " + APPIUM_ENV.get("SAUCELABS_USERNAME"));
        System.out.println("### SauceLabs Key: " + APPIUM_ENV.get("SAUCELABS_KEY"));

        return desiredCapabilities;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    /**
     * Wait for a given element (id) to be located on the page.
     * @param id Mobile Element to wait for.
     * @return MobileElement that was found within the waiting period.
     */
    public MobileElement waitForElementById(String id) {
        return (MobileElement) wait.until( ExpectedConditions.presenceOfElementLocated( MobileBy.id( id)));
    }
}
