import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Experiment extends ObjectExtension {
    private int id;
    private String title;
    private String description;
    private String result;
    private List<Assignment> assignmentList = new ArrayList<>(); // z atrybutem klasowym
    private static int nextId = 1;

    public Experiment(String title, String description, String result) {
        try {
            setTitle(title);
            setDescription(description);
            setResult(result);
            setId(nextId++);
        } catch (Exception e) {
            nextId--;
            removeFromExtent();
            throw e;
        }
    }

    @Override
    protected void removeFromExtent() {
        while(!assignmentList.isEmpty()){
            assignmentList.getFirst().removeFromExtent();
        }
        super.removeFromExtent();
    }

    public Experiment(String title, String description) {
        try {
            setTitle(title);
            setDescription(description);
            setId(nextId++);
        } catch (Exception e) {
            nextId--;
            e.printStackTrace();
            removeFromExtent();
        }

    }

    //do asocjacji klasowej
    public void addLabTechnician(LabTechnician labTechnician, String status, LocalDate deadlineDate) {
        if (labTechnician == null) {
            throw new IllegalArgumentException("Lab Technician cannot be null");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
        if (deadlineDate == null) {
            throw new IllegalArgumentException("Deadline date cannot be null");
        }

        Assignment assignment = new Assignment(labTechnician, this, status, deadlineDate);
        assignmentList.add(assignment);
        labTechnician.addAssignment(assignment);
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
    public void removeLabTechnician(LabTechnician labTechnician) {
        if (labTechnician == null) {
            throw new IllegalArgumentException("Experiment cannot be null");
        }

        Assignment assignmentToRemove = assignmentList.stream()
                .filter(a -> labTechnician.equals(a.getLabTechnician()))
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


    protected void setId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.id = id;
    }

    public void setTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank or empty");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if(description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank or empty");
        }
        this.description = description;
    }

    public void setResult(String result) {
        if(result != null && !result.isBlank()){
            this.result = result;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public List<Assignment> getAssignmentList() {
        return Collections.unmodifiableList(assignmentList);
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
