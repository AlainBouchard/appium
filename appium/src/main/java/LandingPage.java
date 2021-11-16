import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;

/**
 * Page Object that will be use with the Landing Page.
 */
public class LandingPage {
    private static HopperUtils hopperUtils;

    public LandingPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    /**
     * Wait for the Hotel Button element to be present on the page.
     * @return MobileElement is the element for the button that was found.
     */
    public MobileElement waitForHotelButton() {
        final String ID = "com.hopper.mountainview.play:id/search_hotels";
        return hopperUtils.waitForElementById(ID);
    }
}
