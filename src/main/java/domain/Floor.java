package domain;

import java.util.List;

public class Floor {

    private Integer number;
    private List<Corridor> corridors;

    public Floor(int number, List<Corridor> corridors) {
        this.number = number;
        this.corridors = corridors;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<Corridor> corridors) {
        this.corridors = corridors;
    }
}
