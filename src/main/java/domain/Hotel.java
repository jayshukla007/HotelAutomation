package domain;

import java.util.List;

public class Hotel {

    private String name;
    private List<Floor> floors;

    public Hotel(String name, List<Floor> floors) {
        this.name = name;
        this.floors = floors;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
