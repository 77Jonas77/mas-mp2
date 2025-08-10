import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabTechnician extends Person {
//    private List<Experiment> experiments = new ArrayList<>();
    private String specialization;

    private Laboratory laboratory; // LAB(1) <-----> LAB-TECHNICIAN(*) ZWYKLA
    private List<Assignment> assignmentList = new ArrayList<>(); // z klasa asocjacyjna
//    private static String labName = "Main Lab";

    public LabTechnician(String name, String lastName, LocalDate birthDate, Contact contact, String specialization) {
        super(name, lastName, birthDate, contact);
        try {
            setSpecialization(specialization);
        } catch (Exception e) {
            removeFromExtent();
            throw e;
        }
    }

    protected void setLaboratory(Laboratory laboratory) {
        if (laboratory == null) {
            throw new IllegalArgumentException("Laboratory cannot be null");
        }
        this.laboratory = laboratory;
    }

    public void addLabolatory(Laboratory laboratory){
        if (laboratory == null) {
            throw new IllegalArgumentException("Laboratory cannot be null");
        }

        if (this.laboratory != laboratory) {
            if (this.laboratory != null) {
                this.laboratory.removeLabTechnician(this);
            }

            this.laboratory = laboratory;
            laboratory.addLabTechnician(this);
        }
    }

    // asocjacja zwykla
    public void removeLabolatory(Laboratory laboratory){
        if(laboratory != null){
            Laboratory temp = this.laboratory;
            this.laboratory = null;
            temp.removeLabTechnician(this);
        }
    }

    //z klasa asocjacyjna
    public void addExperiment(Experiment experiment, String status, LocalDate deadlineDate) {
        if (experiment == null) {
            throw new IllegalArgumentException("Experiment cannot be null");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
        if (deadlineDate == null) {
            throw new IllegalArgumentException("Deadline date cannot be null");
        }

        Assignment assignment = new Assignment(this, experiment, status, deadlineDate);
        assignmentList.add(assignment);
        experiment.addAssignment(assignment);
    }

    @Override
    protected void removeFromExtent() {
        if(laboratory != null) {
            Laboratory temp = laboratory;
            laboratory = null;
            temp.removeLabTechnician(this);
        }
        for (Assignment assignment : assignmentList) {
            assignment.removeFromExtent();
        }
        super.removeFromExtent();
    }

    protected void addAssignment(Assignment assignment) {
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (assignmentList.contains(assignment)) {
            throw new IllegalArgumentException("Assignment already exists in the list");
        }
        assignmentList.add(assignment);
    }

    //remove
    public void removeExperiment(Experiment experiment) {
        if (experiment == null) {
            throw new IllegalArgumentException("Experiment cannot be null");
        }

        Assignment assignmentToRemove = assignmentList.stream()
                .filter(a -> experiment.equals(a.getExperiment()))
                .findFirst()
                .orElse(null);

        if (assignmentToRemove != null) {
            assignmentToRemove.removeFromExtent();
        }
    }

    protected void removeAssigment(Assignment assignment){
        if(assignment==null){
            throw new IllegalArgumentException("Assignment cannot be null");
        }

        assignmentList.remove(assignment);
    }

    public static List<LabTechnician> findBySpecialization(String specialization) {
        return ObjectExtension.getExtentFromClass(LabTechnician.class).stream()
                .filter(lt -> lt.getSpecialization().equalsIgnoreCase(specialization))
                .toList();
    }

//    public void addExperiment(Experiment experiment) {
//        experiments.add(experiment);
//    }

//    public void removeExperimentById(int experimentId) {
//        if (experiments.removeIf(experiment -> experiment.getId() == experimentId)) {
//            System.out.println("Experiment with id: " + experimentId + " removed");
//        } else {
//            System.out.println("Experiment with id: " + experimentId + " not found");
//        }
//    }

//    public void addResultToExperiment(int experimentId, String result) {
//        experiments.stream()
//                .filter(experiment -> experiment.getId() == experimentId)
//                .findFirst()
//                .ifPresent(experiment -> experiment.setResult(result));
//    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
        this.specialization = specialization;
    }

//    public static void setLabName(String labName) {
//        if (labName == null || labName.isEmpty()) {
//            throw new IllegalArgumentException("Lab name cannot be empty");
//        }
//        LabTechnician.labName = labName;
//    }

//    public List<Experiment> getExperiments() {
//        return Collections.unmodifiableList(experiments);
//    }

    public String getSpecialization() {
        return specialization;
    }

    /*public static String getLabName() {
        return labName;
    }*/

    public List<Assignment> getAssignmentList() {
        return Collections.unmodifiableList(assignmentList);
    }

    @Override
    public String toString() {
        return "LabTechnician{" +
                ", specialization='" + specialization + '\'' +
                ", laboratory=" + laboratory +
                '}';
    }
}
