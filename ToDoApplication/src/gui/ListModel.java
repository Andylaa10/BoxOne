package gui;

import be.Task;
import bll.TaskManager;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ListModel {
    private TaskManager taskManager = new TaskManager();
    private ObservableList<TaskModel> taskLists = FXCollections.observableArrayList();
    private ObjectProperty<TaskModel> selectedTask = new SimpleObjectProperty<>();
    private StringProperty newDescription = new SimpleStringProperty();
    private ObjectProperty<LocalDate> newDeadline = new SimpleObjectProperty<>();
    private StringProperty newAssignedTo = new SimpleStringProperty();

    public ListModel() throws IOException {
        List<TaskModel> taskModels = taskManager.getAllTask().stream().map(t ->
                new TaskModel(t.getId(), t.getDescription(), t.getDeadline(), t.getAssignee())).toList();
        taskLists = FXCollections.observableArrayList(taskModels);
    }


    public void add() throws IOException {
        Task task = new Task(0, false, newDescription.get(), newDeadline.get(), newAssignedTo.get());
        taskManager.addTask(task);
        TaskModel taskModel = new TaskModel(task.getId(), task.getDescription(), task.getDeadline(), task.getAssignee());
        taskLists.add(new TaskModel(task.getId(), getNewDescription().get(), getNewDeadLine().get(), getNewAssignedTo().get()));
    }


    public StringProperty getNewDescription(){
        return newDescription;
    }

    public void remove() throws IOException {
        Task task = selectedTask.get().convertToTask();
        taskManager.removeTask(task);
        taskLists.remove(selectedTask.get());
    }

    public ObjectProperty<LocalDate> getNewDeadLine(){
        return newDeadline;
    }

    public StringProperty getNewAssignedTo() {
        return newAssignedTo;
    }

    public ObservableList<TaskModel> getToDo() {
        return taskLists;
    }

    public ObjectProperty<TaskModel> getSelectedTask(){
        return selectedTask;
    }

}

