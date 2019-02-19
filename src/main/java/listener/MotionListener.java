package listener;

import controller.Controller;

public class MotionListener implements Listener{
    private Controller controller;

    public MotionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void onEvent(int floorNumber, int corridorId) {
        controller.onMotionEvent(floorNumber, corridorId);
    }
}
