package utils;

import domain.Appliance;
import domain.Corridor;
import domain.Floor;
import domain.Hotel;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HotelInitializer {

 public Hotel initialize(Integer numberOfFloors, Integer numberOfMainCorridor, Integer numberOfSubCorridor, String hotelName) {
     List<Floor> floors = new ArrayList<>();
     IntStream.rangeClosed(1, numberOfFloors).forEach(floorNumber -> {
         List<Corridor> corridors = new ArrayList<>();
         IntStream.rangeClosed(1, numberOfMainCorridor).forEach(mainCorridor -> {
             updateCorridors(corridors, mainCorridor, Constants.MAIN_CORRIDOR);

         });
         IntStream.rangeClosed(1, numberOfSubCorridor).forEach(subCorridor -> {
             updateCorridors(corridors, subCorridor, Constants.SUB_CORRIDOR);
         });

         Floor floor = new Floor(floorNumber, corridors);
         floors.add(floor);
     });

     return new Hotel(hotelName, floors);

 }

    private void updateCorridors(List<Corridor> corridors, int id, String type) {
        Appliance light = null;
        if(type.equalsIgnoreCase(Constants.MAIN_CORRIDOR)) {
            light = new Appliance(id, Constants.LIGHT, Constants.LIGHT_POWER, Constants.ON);
        } else {
            light = new Appliance(id, Constants.LIGHT, Constants.LIGHT_POWER, Constants.OFF);
        }
        Appliance ac = new Appliance(id, Constants.AC, Constants.AC_POWER, Constants.ON);

        List<Appliance> appliances = new ArrayList<>();
        appliances.add(ac);
        appliances.add(light);

        Corridor corridor = new Corridor(id, type, appliances);
        corridors.add(corridor);
    }

}
