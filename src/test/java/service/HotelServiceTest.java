package service;

import domain.Corridor;
import domain.Hotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import utils.Constants;
import utils.HotelInitializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HotelServiceTest {

    private HotelInitializer hotelInitializer;
    private HotelService hotelService;

    @Before
    public void setUp() {
        hotelInitializer = new HotelInitializer();
        hotelService = new HotelServiceImpl();
    }

    @Test
    public void shouldChangeApplianceStateInCaseOfMovementEvenIfPowerConsumptionIsLessThanMaxPower() {
        Hotel hotel = hotelInitializer.initialize(2,1,2,"HotelName");

        hotelService.controlAppliances(hotel.getFloors().get(0), 2);

        Assert.assertFalse(isApplianceStateSame(hotel));
    }

    @Test
    public void shouldChangeApplianceStateIfPowerConsumptionIsMoreThanMaxPower() {
        Hotel hotel = hotelInitializer.initialize(2,1,2,"HotelName");
        hotel.getFloors().stream().forEach(floor -> floor.getCorridors().stream().forEach(corridor -> corridor.getAppliances().stream().forEach(appliance -> appliance.setPowerRequired(15))));

        hotelService.controlAppliances(hotel.getFloors().get(0), 2);
        Assert.assertFalse(isApplianceStateSame(hotel));
    }

    @Test
    public void shouldResetApplianceState() {
        Hotel hotel = hotelInitializer.initialize(2,1,2,"HotelName");
        hotel.getFloors().forEach(floor -> floor.getCorridors().forEach(corridor -> {
                    if(corridor.getId().equals(2) && floor.getNumber()==1)
                        corridor.getAppliances().forEach(appliance -> {
                            if(appliance.getType().equalsIgnoreCase(Constants.LIGHT))
                                appliance.switchON();
                        });
                }
        ));

        hotelService.resetApplianceState(hotel.getFloors().get(0), 2);
        Assert.assertTrue(isApplianceStateSame(hotel));
    }

    private boolean isApplianceStateSame(Hotel hotel){
        Hotel hotelBeforeChange = hotelInitializer.initialize(2,1,2,"HotelName");
        List<Boolean> stateNotChanged = new ArrayList<>();
        List<Corridor> subcorridorsBeforeChange = hotelBeforeChange.getFloors().stream().flatMap(floor -> floor.getCorridors().stream().filter(corridor -> corridor.getType().equalsIgnoreCase(Constants.SUB_CORRIDOR))).collect(Collectors.toList());
        List<Corridor> subcorridorsAfterChange = hotel.getFloors().stream().flatMap(floor -> floor.getCorridors().stream().filter(corridor -> corridor.getType().equalsIgnoreCase(Constants.SUB_CORRIDOR))).collect(Collectors.toList());

        subcorridorsBeforeChange.forEach(sub -> {
            subcorridorsAfterChange.forEach(sub1 -> {
                if(sub.getId() == sub1.getId()){
                    sub.getAppliances().forEach(appliance -> {
                        sub1.getAppliances().forEach(appliance1 -> {
                            if(appliance.getId() == appliance1.getId() && appliance.getType().equalsIgnoreCase(appliance1.getType()))
                                 if(appliance.getStatus().equalsIgnoreCase(appliance1.getStatus())){
                                     stateNotChanged.add(true);
                                }else {
                                     stateNotChanged.add(false);
                                 }
                        });
                    });
                }
            });
        });

    return stateNotChanged.stream().reduce((a,b)-> a && b).get();
    }
}