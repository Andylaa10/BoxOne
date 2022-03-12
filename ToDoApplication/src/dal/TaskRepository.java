package dal;

import be.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskRepository {

        private static final String FILE_SEPERATOR = ";";
        private static final String TASK_SOURCE = "data/task.txt";

        private void ensureFileExists() throws IOException{
            File file = new File(TASK_SOURCE);
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        }

        public List<Task> getAllTask() throws IOException{
            ensureFileExists();
            try(BufferedReader br = new BufferedReader(new FileReader(TASK_SOURCE))){
                List<Task> allTask = new ArrayList<>();

                while (true){
                    String aLineOfText = br.readLine();
                    if (aLineOfText == null){
                        break;
                    }
                    else if (!aLineOfText.isEmpty()){
                        try{
                            String[] taskArray = aLineOfText.split(FILE_SEPERATOR);
                            int id = Integer.parseInt(taskArray[0].trim());
                            String description = taskArray[1].trim();
                            LocalDate deadline = LocalDate.parse(taskArray[2].trim());
                            String assignee = taskArray[3].trim();
                            boolean completed = Boolean.parseBoolean(taskArray[4].trim());

                            Task task = new Task(id, completed, description, deadline, assignee);
                            allTask.add(task);
                        }
                        catch (Exception e){
                            //Skip row
                        }
                    }
                }
                return allTask;
            }
        }

        public void createTask(Task task) throws IOException{
            List<Task> tasks = getAllTask();
            int newId = 1;
            if(!tasks.isEmpty()){
                newId = tasks.stream().max(Comparator.comparing(Task::getId)).get().getId()+1;
            }
            task.setId(newId);
            tasks.add(task);

            writeAllTasks(tasks);
        }

        public void deleteTask(Task task) throws IOException{
            List<Task> tasks = getAllTask();
            tasks.removeIf(t -> t.getId() == task.getId());
            writeAllTasks(tasks);
        }

        public void updateTask(Task task) throws IOException{
            List<Task> tasks = getAllTask();
            tasks.removeIf(t -> t.getId() == task.getId());
            tasks.add(task);
            writeAllTasks(tasks);
        }

        private void writeAllTasks(List<Task> tasks) throws IOException{
            FileOutputStream fos = new FileOutputStream(TASK_SOURCE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for(Task task : tasks){
                bw.write(Integer.toString(task.getId()));
                bw.write(FILE_SEPERATOR);
                bw.write(task.getDescription());
                bw.write(FILE_SEPERATOR);
                bw.write(task.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                bw.write(FILE_SEPERATOR);
                bw.write(task.getAssignee());
                bw.write(FILE_SEPERATOR);
                bw.write(task.isCompleted() ? "true" : "false");
                bw.newLine();
            }

            bw.close();
        }
}
