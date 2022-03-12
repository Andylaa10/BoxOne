package bll;

import be.Task;
import dal.TaskRepository;

import java.io.IOException;
import java.util.List;

public class TaskManager {
    private TaskRepository taskRespository = new TaskRepository();

    public List<Task> getAllTask() throws IOException{
        return taskRespository.getAllTask();
    }

    public void postPoneTask(Task task, int numbersOfDays) throws IOException{
        task.setDeadline(task.getDeadline().plusDays(numbersOfDays));
        taskRespository.updateTask(task);
    }

    public void addTask(Task task) throws IOException{
        taskRespository.createTask(task);
    }

    public void removeTask(Task task) throws IOException{
        taskRespository.deleteTask(task);
    }

    public void completeTask(Task task) throws IOException{
        task.setCompleted(true);
        taskRespository.updateTask(task);
    }

}