package service;

import domain.Appliance;
import domain.Corridor;
import domain.Floor;
import utils.Constants;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    @Override
    public void controlAppliances(Floor floor, Integer corridorId) {
        Integer totalPower = floor.getCorridors().stream().flatMap(corridor -> corridor.getAppliances().stream()
                .map(appliance -> {
                    if (appliance.getStatus().equals(Constants.ON))
                        return appliance.getPowerRequired();
                    else
                        return 0;
                })).mapToInt(i -> i).sum();

        Integer maxPower = getMaxPower(floor);

        toggleLightsSwitch(floor, corridorId, Constants.ON);

        if (totalPower > maxPower) {
            Integer excessPowerConsumed = totalPower - maxPower;
            Integer numberOfACstoBeSwitchedOFF = (int) Math.round((double) excessPowerConsumed / (double) Constants.AC_POWER);

            Corridor corridor=floor.getCorridors().stream().filter(cor -> !(cor.getId().equals(corridorId) || cor.getType().equals(Constants.MAIN_CORRIDOR))).findFirst().get();
            toggleAcSwitch(corridor, Constants.OFF, Optional.of(numberOfACstoBeSwitchedOFF));
        }
    }

    @Override
    public void resetApplianceState(Floor floor, Integer corridorId) {
        toggleLightsSwitch(floor, corridorId, Constants.OFF);
        Corridor corridor=floor.getCorridors().stream().filter(cor -> cor.getId().equals(corridorId)).findFirst().get();
        toggleAcSwitch(corridor, Constants.ON, Optional.empty());
    }

    private void toggleLightsSwitch(Floor floor, Integer corridorId, String status) {
        floor.getCorridors().stream().filter(corridor -> corridor.getId().equals(corridorId)).forEach(corridor -> {
            corridor.getAppliances().stream().filter(appliance -> appliance.getType().equals(Constants.LIGHT)).forEach(light -> toggleSwitch(status, light));
        });
    }

    private void toggleAcSwitch(Corridor corridor, String status, Optional<Integer> numberOfACstoBeSwitchedOFF) {

        List<Appliance> appliances = corridor.getAppliances().stream()
                .filter(app -> (!(app.getStatus().equals(status)) && app.getType().equals(Constants.AC)))
                .collect(Collectors.toList());

        if (numberOfACstoBeSwitchedOFF.isPresent())
            appliances.stream().limit(numberOfACstoBeSwitchedOFF.get()).forEach(ac -> toggleSwitch(status, ac));
        else
            appliances.forEach(ac -> toggleSwitch(status, ac));
    }

    private void toggleSwitch(String status, Appliance appliance) {
        if (status.equalsIgnoreCase(Constants.ON))
            appliance.switchON();
        else
            appliance.switchOFF();
    }

    private Integer getMaxPower(Floor floor){
        return (floor.getCorridors().stream().filter(corridor -> corridor.getType().equals(Constants.MAIN_CORRIDOR))
                .collect(Collectors.toList()).size() * 15)+(floor.getCorridors().stream()
                .filter(corridor -> corridor.getType().equals(Constants.SUB_CORRIDOR)).collect(Collectors.toList()).size() * 10) ;
    }
}
