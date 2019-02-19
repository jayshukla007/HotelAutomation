package controller;

import domain.Floor;
import domain.Hotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import service.HotelService;
import utils.HotelInitializer;

public class ApplianceControllerTest {
    private HotelInitializer hotelInitializer;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private ApplianceController applianceController;

    @Before
    public void setUp() {
        hotelInitializer = new HotelInitializer();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldControlAppliancesInCaseOfMotion() {

        Hotel hotel = hotelInitializer.initialize(2,1,2, "Abc");
        Mockito.doNothing().when(hotelService).controlAppliances(hotel.getFloors().get(1),1);
        applianceController = new ApplianceController(hotel, hotelService);

        applianceController.onMotionEvent(1,1);

        Mockito.verify(hotelService, Mockito.times(1)).controlAppliances(Matchers.any(Floor.class), Matchers.anyInt());
    }

    @Test
    public void shouldResetStateIfNoMotionDetected() {

        Hotel hotel = hotelInitializer.initialize(2,1,2, "Abc");
        hotel.getFloors().get(1).getCorridors().get(1).getAppliances().get(1).switchOFF();
        Mockito.doNothing().when(hotelService).resetApplianceState(hotel.getFloors().get(1),1);

        applianceController = new ApplianceController(hotel, hotelService);

        applianceController.resetMotionEvent(1,1);

        Mockito.verify(hotelService, Mockito.times(1)).resetApplianceState(Matchers.any(Floor.class), Matchers.anyInt());
    }

    // Ignore this test, written only for printing status
    @Test
    public void shouldPrintInitialStatus() {
        Hotel hotel = hotelInitializer.initialize(2,1,2, "Abc");
        applianceController = new ApplianceController(hotel, hotelService);

        applianceController.printStatus();

        Assert.assertTrue(true);
    }

}