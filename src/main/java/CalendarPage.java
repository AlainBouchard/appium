import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;

public class CalendarPage {
    private static HopperUtils hopperUtils;

    public CalendarPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    public MobileElement waitForSelectDatesButton() {
        final String BUTTON_ID = "com.hopper.mountainview.play:id/selectTheseDatesTwoLinesButton";
        return hopperUtils.waitForElementById(BUTTON_ID);
    }
}
