import java.io.Serializable;

public class Equipment extends ObjectExtension {
    private String name;
    private String serialNumber;
    private Laboratory laboratory; // asocjacja kwalifikowana

    public Equipment(String name, String serialNumber, Laboratory laboratory) {
        try {
            setName(name);
            setSerialNumber(serialNumber);
            setLaboratory(laboratory);
        } catch (Exception e) {
            removeFromExtent();
            throw e;
        }
    }

    protected void setLaboratory(Laboratory laboratory) {
        if(laboratory == null) {
            throw new IllegalArgumentException("Laboratory cannot be null");
        }

        this.laboratory = laboratory;
        laboratory.addEquipment(this);
    }

    public void changeLabolatory(Laboratory laboratory) {
        if (laboratory == null) {
            throw new IllegalArgumentException("Labolatory cannot be null");
        }
        if(this.laboratory!=laboratory){
            if(this.laboratory!=null){
                this.laboratory.removeEquipment(this);
            }
            this.laboratory = laboratory;
            laboratory.addEquipment(this);
        }
    }


    @Override
    protected void removeFromExtent() {
        if (laboratory != null) {
            Laboratory temp = laboratory;
            laboratory = null;
            temp.removeEquipment(this);
        }
        super.removeFromExtent();
    }

    //    public void addLaboratory(Laboratory laboratory) {
//        if (laboratory == null) {
//            throw new IllegalArgumentException("Laboratory cannot be null");
//        }
//        this.laboratory = laboratory;
//        if (!laboratory.getEquipmentMap().containsKey(this.getSerialNumber())) {
//            laboratory.addEquipment(this);
//        }
//    }
//
//    public void removeLaboratory(Laboratory laboratory) {
//        this.laboratory = null;
//        if(laboratory.getEquipmentMap().containsKey(this.getSerialNumber())) {
//            laboratory.removeEquipment(this);
//        }
//    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    private void setSerialNumber(String serialNumber) {
        if (serialNumber == null || serialNumber.isBlank()) {
            throw new IllegalArgumentException("Serial number cannot be null or blank");
        }
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
