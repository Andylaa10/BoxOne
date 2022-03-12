package gui;

import be.Task;
import bll.TaskManager;
import javafx.beans.property.*;

import java.io.IOException;
import java.time.LocalDate;

public class TaskModel {


    private TaskManager taskManager = new TaskManager();
    private int id;
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> deadline = new SimpleObjectProperty<>();
    private StringProperty assignedTo = new SimpleStringProperty();
    private BooleanProperty completed = new SimpleBooleanProperty();


    public TaskModel(int id, String description, LocalDate deadline, String assignedTo) {

        this.id = id;
        this.description.set(description);
        this.deadline.set(deadline);
        this.assignedTo.set(assignedTo);

    }

    public StringProperty getDescription(){
        return description;
    }

    public ObjectProperty<LocalDate> getDeadline(){
        return deadline;
    }

    public StringProperty getAssignedTo(){
        return assignedTo;
    }

    public BooleanProperty getNewCompleted(){
        return completed;
    }

    public Task convertToTask(){

        Task task = new Task(id, completed.get(), description.get(), deadline.get(), assignedTo.get());
        return task;
    }

    public void postPone(int numberOfDays) throws IOException {
        Task task = convertToTask();
        taskManager.postPoneTask(task, numberOfDays);
        deadline.set(task.getDeadline());
    }

    public void complete() throws IOException {
        Task task = convertToTask();
        taskManager.completeTask(task);

        completed.set(task.isCompleted());

    }
}
