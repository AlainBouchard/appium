import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookAnHotel {
    private HopperUtils hopperUtils;
    private AndroidDriver driver;
    private LandingPage landingPage;
    private HotelPage hotelPage;
    private CalendarPage calendarPage;

    @BeforeAll
    public void setUp() throws Exception {
        hopperUtils = new HopperUtils();
        driver = hopperUtils.getDriver();

        landingPage = new LandingPage(driver);
        hotelPage = new HotelPage(driver);
        calendarPage = new CalendarPage(driver);
    }

    @AfterAll
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void bookAnHotelTest() throws InterruptedException {
        landingPage
                .waitForHotelButton()
                .click();

        hotelPage
                .waitForSearchHotelEditText()
                .sendKeys("Buenos Aires");

        hotelPage.waitForSearchHotelOptions();

        MobileElement location = hotelPage.selectHotelLocation("Buenos Aires, Argentina");
        assertNotNull(location);

        location.click();

        calendarPage.waitForSelectDatesButton();
    }
}
