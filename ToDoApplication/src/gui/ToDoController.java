package gui;

import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class ToDoController {

    private ListModel model = new ListModel();

    @FXML
    private TableView<TaskModel> tvToDo;

    @FXML
    private TableColumn<TaskModel, Boolean> tcCompleted;

    @FXML
    private TableColumn<TaskModel, String> tcDescription;

    @FXML
    private TableColumn<TaskModel, LocalDate> tcDeadline;

    @FXML
    private TableColumn<TaskModel, String> tcAssignee;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtAssignedTo;

    @FXML
    private DatePicker dpDeadline;



    public ToDoController() throws IOException {
    }

    public void initialize() {
        tvToDo.setItems(model.getToDo());

        tcCompleted.setCellValueFactory(taskList -> taskList.getValue().getNewCompleted());
        tcDescription.setCellValueFactory(taskList ->taskList.getValue().getDescription());
        tcDeadline.setCellValueFactory(taskList ->taskList.getValue().getDeadline());
        tcAssignee.setCellValueFactory(taskList ->taskList.getValue().getAssignedTo());

        model.getSelectedTask().bind(tvToDo.getSelectionModel().selectedItemProperty());
        model.getNewDescription().bindBidirectional(txtDescription.textProperty());
        model.getNewDeadLine().bindBidirectional(dpDeadline.valueProperty());
        model.getNewAssignedTo().bindBidirectional(txtAssignedTo.textProperty());


    }

    @FXML
    public void btnAddClicked() throws IOException {
        model.add();
    }

    @FXML
    public void btnRemoveClicked() throws IOException {
        model.remove();
    }

    @FXML
    public void btnCompletedClicked() throws IOException {
        model.getSelectedTask().get().complete();
    }

    @FXML
    public void btnPostpone1DayClicked() throws IOException {
        model.getSelectedTask().get().postPone(1);
    }
    @FXML
    public void btnPostpone1WeekClicked() throws IOException {
        model.getSelectedTask().get().postPone(7);
    }

    @FXML
    public void btnPostpone1MouthClicked() throws IOException {
        model.getSelectedTask().get().postPone(31);
    }

}