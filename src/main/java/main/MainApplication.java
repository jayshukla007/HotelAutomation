package main;

import controller.ApplianceController;
import controller.Controller;
import domain.Hotel;
import listener.Listener;
import listener.MotionListener;
import service.HotelService;
import service.HotelServiceImpl;
import utils.HotelInitializer;

public class MainApplication {


    public static void main(String[] args) throws InterruptedException {

        HotelInitializer hotelInitializer = new HotelInitializer();

        Hotel hotel= hotelInitializer.initialize(2,1, 2, "HotelName");
        HotelService hotelService = new HotelServiceImpl();
        Controller controller = new ApplianceController(hotel, hotelService);


        Listener listener = new MotionListener(controller);

        System.out.println("INITIAL STAGE...........");
        controller.printStatus();


        System.out.println("AFTER MOTION DETECTED............");
        listener.onEvent(1, 2);
        controller.printStatus();

        Thread.sleep(4 * 1000);

        listener.onEvent(2, 2);

        System.out.println("AFTER SECOND MOTION DETECTED................");
        controller.printStatus();
    }
}
