import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;

/**
 * Page Object that will be use with the Calendar Page.
 */
public class CalendarPage {
    private static HopperUtils hopperUtils;

    public CalendarPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    /**
     * Wait for the Select Date button to be in the page.
     * @return MobileElement is the Select Date button that has been found.
     */
    public MobileElement waitForSelectDatesButton() {
        final String BUTTON_ID = "com.hopper.mountainview.play:id/selectTheseDatesTwoLinesButton";
        return hopperUtils.waitForElementById(BUTTON_ID);
    }
}
