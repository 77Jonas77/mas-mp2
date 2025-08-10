import java.util.*;

public class Laboratory extends ObjectExtension {
    private String name;
    private String address;
    private String country;

    private List<LabTechnician> labTechnicianList = new ArrayList<>(); // asocjacja zwykla
    private List<Schedule> scheduleList = new ArrayList<>(); // kompozycja
    private Map<String, Equipment> equipmentMap = new HashMap<>(); // asocjacja kwalifikowana

    public Laboratory(String name, String address, String country) {
        try {
            setName(name);
            setAddress(address);
            setCountry(country);
        } catch (Exception e) {
            removeFromExtent();
            throw e;
        }
    }

    @Override
    public void removeFromExtent() {
       while(!labTechnicianList.isEmpty()){
           removeLabTechnician(labTechnicianList.getFirst());
       }
       while(!equipmentMap.isEmpty()){
           removeEquipment(equipmentMap.values().iterator().next());
       }
       while(!scheduleList.isEmpty()){
           removeSchedule(scheduleList.getFirst());
       }

       super.removeFromExtent();
    }

    public Equipment findEquipmentBySerialNumber(String serialNumber) {
        if (serialNumber == null || serialNumber.isBlank()) {
            throw new IllegalArgumentException("Serial number cannot be null or blank");
        }
        return equipmentMap.getOrDefault(serialNumber, null);
    }

    // asocjacja kwalifikowana
    public void addEquipment(Equipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment cannot be null");
        }
        if (!equipmentMap.containsKey(equipment.getSerialNumber())) {
            equipmentMap.put(equipment.getSerialNumber(), equipment);
            equipment.setLaboratory(this);
        }
    }

//    public Equipment getEquipmentBySerialNumber(String serialNumber) {
//        if (serialNumber == null || serialNumber.isBlank()) {
//            throw new IllegalArgumentException("Serial number cannot be null or blank");
//        }
//        return equipmentMap.getOrDefault(serialNumber, null);
//    }

    public void removeEquipment(Equipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment cannot be null");
        }
        if (equipmentMap.remove(equipment.getSerialNumber()) != null) {
            equipment.setLaboratory(null);
        }
    }

    // kompozycja
    public void addSchedule(Schedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule cannot be null");
        }

        if (!scheduleList.contains(schedule)) {
            scheduleList.add(schedule);
            schedule.setLaboratory(this);
        }
    }

    public void removeSchedule(Schedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule cannot be null");
        }
        if (scheduleList.remove(schedule)) {
            schedule.removeFromExtent();
        }
    }

    public void addLabTechnician(LabTechnician labTechnician) {
        if (labTechnician == null) {
            throw new IllegalArgumentException("Lab Technician cannot be null");
        }
        if (!labTechnicianList.contains(labTechnician)) {
            labTechnicianList.add(labTechnician);
            labTechnician.addLabolatory(this);
        }
    }

    public void removeLabTechnician(LabTechnician labTechnician) {
        if (labTechnician == null) {
            throw new IllegalArgumentException("Lab Technician cannot be null");
        }
        if (labTechnicianList.remove(labTechnician)) {
            labTechnician.removeLabolatory(this);
        }
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
        this.address = address;
    }

    public void setCountry(String country) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Equipment> getEquipmentMap() {
        return Collections.unmodifiableMap(equipmentMap);
    }

    public String getCountry() {
        return country;
    }

    public List<LabTechnician> getLabTechnicianList() {
        return Collections.unmodifiableList(labTechnicianList);
    }

    public List<Schedule> getScheduleList() {
        return Collections.unmodifiableList(scheduleList);
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", labTechnicianList=" + labTechnicianList +
                ", scheduleList=" + scheduleList +
                ", equipmentMap=" + equipmentMap +
                '}';
    }
}
