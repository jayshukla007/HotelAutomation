package controller;

import domain.Hotel;
import manager.MotionWatcher;
import service.HotelService;

public class ApplianceController implements Controller {

    private Hotel hotel;
    private HotelService hotelService;

    public ApplianceController(Hotel hotel, HotelService hotelService) {
        this.hotel=hotel;
        this.hotelService = hotelService;
    }

    @Override
    public void onMotionEvent(Integer floorNumber, Integer corridorId) {
        hotel.getFloors().stream().filter(floor -> floor.getNumber().equals(floorNumber)).forEach(floor -> hotelService.controlAppliances(floor, corridorId));
        MotionWatcher watcher = new MotionWatcher(this,floorNumber,corridorId);
        watcher.renewWaitingPeriod();
        new Thread(watcher).start();
    }

    @Override
    public void resetMotionEvent(Integer floorNumber, Integer corridorId) {
        hotel.getFloors().stream().filter(floor -> floor.getNumber().equals(floorNumber)).forEach(floor -> hotelService.resetApplianceState(floor, corridorId));
        System.out.printf("Appliances state restored successfully. ");
    }

    @Override
    public void printStatus() {
        hotel.getFloors().forEach(floor -> {
            System.out.println("Floor "+floor.getNumber());
            floor.getCorridors().forEach(corridor -> {
                System.out.print(corridor.getType()+ " "+ corridor.getId() + " ");
                corridor.getAppliances().forEach(a -> {
                    System.out.print(a.getType() + " " + a.getId() + " " + a.getStatus() + " ");
                });
                System.out.println();
            });
        });
    }
}
