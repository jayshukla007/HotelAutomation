package service;

import domain.Floor;

public interface HotelService {
    void controlAppliances(Floor floor, Integer corridorId);
    void resetApplianceState(Floor floor, Integer corridorId);
}
