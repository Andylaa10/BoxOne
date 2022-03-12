package be;

import javafx.beans.property.IntegerProperty;

import java.time.LocalDate;

public class Task {

    private int id;
    private boolean completed;
    private String description;
    private LocalDate deadline;
    private String assignee;

    public Task(int id, boolean completed, String description, LocalDate deadline, String assignee){
        this.id = id;
        this.completed = completed;
        this.description = description;
        this.deadline = deadline;
        this.assignee = assignee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
