import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;

public class LandingPage {
    private static HopperUtils hopperUtils;

    public LandingPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    public MobileElement waitForHotelButton() {
        final String ID = "com.hopper.mountainview.play:id/search_hotels";
        return hopperUtils.waitForElementById(ID);
    }
}
