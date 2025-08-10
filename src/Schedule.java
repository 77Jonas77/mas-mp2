import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule extends ObjectExtension {
    private String day;
    private LocalDate startDate;
    private LocalDate endDate;
    private Laboratory laboratory;

    public Schedule(String day, LocalDate startDate, LocalDate endDate, Laboratory laboratory)  {
        try {
            setDay(day);
            setStartDate(startDate);
            setEndDate(endDate);
            setLaboratory(laboratory);
        } catch (Exception e) {
            removeFromExtent();
            throw e;
        }
    }

    //kompozycja
    @Override
    public void removeFromExtent() {
        if(laboratory != null) {
            Laboratory temp = laboratory;
            laboratory = null;
            temp.removeSchedule(this);
        }
        super.removeFromExtent();
    }

    public void setDay(String day) {
        if (day == null || day.isBlank()) {
            throw new IllegalArgumentException("Day cannot be null or blank");
        }
        this.day = day;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        this.endDate = endDate;
    }

    protected void setLaboratory(Laboratory laboratory) {
        if (laboratory == null ) { // || !ObjectExtension.getExtentFromClass(Laboratory.class).contains(laboratory)
            throw new IllegalArgumentException("Laboratory cannot be null");
        }
        this.laboratory = laboratory;
        laboratory.addSchedule(this);
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public String getDay() {
        return day;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
