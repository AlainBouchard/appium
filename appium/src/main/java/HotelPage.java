import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Page Object that will be use with the Hotel Page.
 */
public class HotelPage {
    private static HopperUtils hopperUtils;
    private static WebDriverWait wait;

    public HotelPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    /**
     * Wait for the Search Hotel Edit Text element to be present on the page.
     * @return MobileElement is the element that was found.
     */
    public MobileElement waitForSearchHotelEditText() {
        final String ID = "com.hopper.mountainview.play:id/locationSearchEditText";
        return hopperUtils.waitForElementById(ID);
    }

    /**
     * Wait for the Search Hotel Options element to be present on the page.
     * @return MobileElement is the Search Hotel Options element that was found.
     */
    public MobileElement waitForSearchHotelOptions() {
        final String OPTIONS_ID = "com.hopper.mountainview.play:id/options";
        return hopperUtils.waitForElementById(OPTIONS_ID);
    }

    /**
     * Select an Hotel Location from all the Hotel Options.
     * @param location is the location to search for, example "Buenos Aires, Argentina".
     * @return MobileElement that matches the given localtion String.
     */
    public MobileElement selectHotelLocation(String location) {
        final String TEXT_VIEW_ID = "android.widget.TextView";

        List<MobileElement> elements = waitForSearchHotelOptions()
                .findElements( MobileBy.className( TEXT_VIEW_ID));

        return elements.stream()
                .filter(element -> location.equals( element.getText()))
                .findAny()
                .orElse(null);
    }
}
