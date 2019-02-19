package domain;

public class Appliance {
    private Integer id;
    private String type;
    private Integer powerRequired;
    private String status;


    public Appliance(Integer id, String type, Integer powerRequired, String status) {
        this.id = id;
        this.type = type;
        this.powerRequired = powerRequired;
        this.status = status;
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


    public void switchON(){
        status="ON";
    }
    public  void switchOFF(){
        status="OFF";
    }
    public void setType(String type) {
        this.type = type;
    }

    public Integer getPowerRequired() {
        return powerRequired;
    }


    public void setPowerRequired(Integer powerRequired) {
        this.powerRequired = powerRequired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
