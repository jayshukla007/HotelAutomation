package manager;

import controller.Controller;

public class MotionWatcher implements Runnable {
    private Controller controller;


    private static int waitTimeinSeconds = 10;
    private  Integer floorId;
    private  Integer corridorId;


    private volatile boolean gotoSleep = true;

    public MotionWatcher(Controller controller, Integer floorId,Integer corridorId) {
        this.controller = controller;
        this.floorId = floorId;
        this.corridorId=corridorId;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (gotoSleep) {
            try {
                Thread.sleep(waitTimeinSeconds * 1000);
                gotoSleep = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Waited for " + (System.currentTimeMillis() - startTime) + " millieseconds.......");
        controller.resetMotionEvent(floorId,corridorId);
    }

    public  void renewWaitingPeriod() {
        gotoSleep = true;
    }
}
