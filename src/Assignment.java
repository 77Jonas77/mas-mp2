import java.io.Serializable;
import java.time.LocalDate;

public class Assignment extends ObjectExtension {
    private LabTechnician technician;
    private Experiment experiment;
    private String status;
    private LocalDate deadlineDate;

    public Assignment(LabTechnician technician, Experiment experiment, String status, LocalDate deadlineDate) {
        try {
            setLabTechnician(technician);
            setExperiment(experiment);
            setStatus(status);
            setDeadlineDate(deadlineDate);
        } catch (Exception e) {
            removeFromExtent();
            throw e;
        }
    }

    @Override
    public void removeFromExtent() {
        Experiment temp = experiment;
        LabTechnician temp2 = technician;
        if(experiment != null) {
//            experiment = null;
            temp.removeAssigment(this);
        }
        if(technician != null) {
            /*technician = null;*/
            temp2.removeAssigment(this);
        }
        super.removeFromExtent();
    }

    protected void setLabTechnician(LabTechnician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Lab Technician cannot be null");
        }
        this.technician = technician;
    }

    protected void setExperiment(Experiment experiment) {
        if (experiment == null) {
            throw new IllegalArgumentException("Experiment cannot be null");
        }
        this.experiment = experiment;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
        this.status = status;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        if (deadlineDate == null) {
            throw new IllegalArgumentException("Deadline date cannot be null");
        }
        this.deadlineDate = deadlineDate;
    }


    public LabTechnician getLabTechnician() {
        return technician;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "technician=" + technician +
                ", experiment=" + experiment +
                ", status='" + status + '\'' +
                ", deadlineDate=" + deadlineDate +
                '}';
    }
}
