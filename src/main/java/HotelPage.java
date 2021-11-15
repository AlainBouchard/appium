import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.List;

public class HotelPage {
    private static HopperUtils hopperUtils;
    private static WebDriverWait wait;

    public HotelPage(AndroidDriver driver) throws MalformedURLException {
        hopperUtils = new HopperUtils();
    }

    public MobileElement waitForSearchHotelEditText() {
        final String ID = "com.hopper.mountainview.play:id/locationSearchEditText";
        return hopperUtils.waitForElementById(ID);
    }

    public MobileElement waitForSearchHotelOptions() {
        final String OPTIONS_ID = "com.hopper.mountainview.play:id/options";
        return hopperUtils.waitForElementById(OPTIONS_ID);
    }

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
