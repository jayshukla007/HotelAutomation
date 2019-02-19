package domain;

import java.util.List;

public class Corridor {

    private Integer id;
    private String type;
    private List<Appliance> appliances;

    public Corridor(Integer id, String type, List<Appliance> appliances) {
        this.id = id;
        this.type = type;
        this.appliances = appliances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }
}
