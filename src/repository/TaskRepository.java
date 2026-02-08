package repository;

import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final Path FILE_PATH = Path.of("tasks.json");

    public List<Task> loadTasks(){
        List<Task> stored_tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(FILE_PATH).trim();

            if (jsonContent.isEmpty() || jsonContent.equals("[]")) {
                return stored_tasks;
            }

            String contentWithoutBrackets = jsonContent.substring(
                    jsonContent.indexOf("[") + 1,
                    jsonContent.lastIndexOf("]")
            ).trim();

            if (contentWithoutBrackets.isEmpty()) {
                return stored_tasks;
            }

            String[] taskList = contentWithoutBrackets.split("},");
            for (String taskJson : taskList) {
                String cleanedJson = taskJson.trim();
                if (!cleanedJson.endsWith("}")) {
                    cleanedJson += "}";
                }
                stored_tasks.add(Task.fromJson(cleanedJson));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return stored_tasks;
    }

    public void saveTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());
            if(i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        try {
            Files.writeString(FILE_PATH, sb.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
