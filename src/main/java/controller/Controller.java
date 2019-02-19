package controller;

public interface Controller {

void onMotionEvent(Integer floorNumber, Integer corridorId);
void resetMotionEvent(Integer floorNumber, Integer corridorId);
void printStatus();

}
