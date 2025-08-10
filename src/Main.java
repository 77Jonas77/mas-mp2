import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        //zwykla asocjacja test
//        LabTechnician labTechnician1 = new LabTechnician("John", "Doe", LocalDate.of(2004, 1, 13), new Contact("john@doe.pl", "+48123456789"), "Physics");
//        Laboratory laboratory = new Laboratory("Main Lab", "Main Street 1", "Poland");
//
//        labTechnician1.addLabolatory(laboratory);
//
//        System.out.println(labTechnician1);
//        System.out.println(laboratory);
//
//        laboratory.removeLabTechnician(labTechnician1);
//        System.out.println(laboratory);
//        System.out.println(labTechnician1);
//
//        System.out.println(ObjectExtension.getExtentFromClass(LabTechnician.class));
//        System.out.println(ObjectExtension.getExtentFromClass(Laboratory.class));

        //asocjacja z klasa asocjacyjna test

//        Experiment experiment1 = new Experiment("Experiment1", "Description1", "Result1");
//        LabTechnician labTechnician2 = new LabTechnician("Pika", "Pika", LocalDate.of(2002, 12, 16), new Contact("xddd123@gmail.pl", "+48123456123"), "Lighting");
//
//        experiment1.addLabTechnician(labTechnician2, "In progress", LocalDate.of(2021, 12, 31));
//
//        System.out.println(experiment1.getAssignmentList());
//        System.out.println(labTechnician2.getAssignmentList());
//
//        experiment1.removeLabTechnician(labTechnician2);
//
//        System.out.println(experiment1.getAssignmentList());
//        System.out.println(labTechnician2.getAssignmentList());
//
//        labTechnician2.addExperiment(experiment1, "In progress", LocalDate.of(2021, 12, 31));
//
//        System.out.println(experiment1.getAssignmentList());
//        System.out.println(labTechnician2.getAssignmentList());
//
//        labTechnician2.removeExperiment(experiment1);
//
//        System.out.println(experiment1.getAssignmentList());
//        System.out.println(labTechnician2.getAssignmentList());

        // ascjacja kwalifikowana test
//        Equipment equipment1 = new Equipment("Serial1", "12345");
//        Laboratory laboratory1 = new Laboratory("Main Lab", "Main Street 1", "Poland");
//
//        laboratory1.addEquipment(equipment1);
//        System.out.println(laboratory1.getEquipmentMap());
//        System.out.println(equipment1.getLaboratory());
//
//        laboratory1.removeEquipment(equipment1);
//        System.out.println(laboratory1.getEquipmentMap());
//        System.out.println(equipment1.getLaboratory());
//
//        Laboratory laboratory1 = new Laboratory("Main Lab", "Main Street 1", "Poland");
//        Equipment equipment1 = new Equipment("Serial1", "12345", laboratory1);
//
//        System.out.println(laboratory1.getEquipmentMap());
//        System.out.println(equipment1.getLaboratory());
//
//        equipment1.setLaboratory(null);
//
//        System.out.println(laboratory1.getEquipmentMap());
//        System.out.println(equipment1.getLaboratory());

        // kompozycja test

//        Laboratory laboratory1 = new Laboratory("Main Lab", "Main Street 1", "Poland");
//        Schedule schedule1 = new Schedule("Monday", LocalDate.of(2021, 12, 31), LocalDate.of(2022, 1, 31), laboratory1);
//        Schedule schedule2 = new Schedule("Tuesday", LocalDate.of(2021, 12, 31), LocalDate.of(2022, 1, 31), laboratory1);
//
//        laboratory1.addSchedule(schedule1);
//        System.out.println(laboratory1.getScheduleList());
//        System.out.println(schedule1.getLaboratory());
//
//        laboratory1.removeSchedule(schedule1);
//        System.out.println(laboratory1.getScheduleList());
//        System.out.println(schedule1.getLaboratory());
//
//        laboratory1.addSchedule(schedule1);
//        laboratory1.addSchedule(schedule2);
//        System.out.println(laboratory1.getScheduleList());
//
//        laboratory1.removeFromExtent();
//        System.out.println(laboratory1.getScheduleList());
//
//        System.out.println(ObjectExtension.getExtentFromClass(Laboratory.class));
//        System.out.println(ObjectExtension.getExtentFromClass(Schedule.class));
        Laboratory laboratory1 = new Laboratory("Main Lab", "Main Street 1", "Poland");
        Schedule schedule1 = new Schedule("Monday", LocalDate.of(2021, 12, 31), LocalDate.of(2022, 1, 31), laboratory1);
        Schedule schedule2 = new Schedule("Monday", LocalDate.of(2021, 12, 31), LocalDate.of(2022, 1, 31), laboratory1);

        System.out.println(laboratory1.getScheduleList());
        System.out.println(schedule1.getLaboratory());

        laboratory1.removeFromExtent();
//        System.out.println(laboratory1.getScheduleList());
//        System.out.println(schedule1.getLaboratory());

        System.out.println(ObjectExtension.getExtentFromClass(Laboratory.class));
        System.out.println(ObjectExtension.getExtentFromClass(Schedule.class));
    }
}