package Service;

import exception.NoSuchTaskException;
import model.Task;
import repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository = new TaskRepository();


    @Override
    public void add(String description) {
        List<Task> tasks = repository.loadTasks();
        Task task = new Task(description);
        tasks.add(task);
        repository.saveTasks(tasks);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    @Override
    public void update(int id, String description) {
        List<Task> tasks = repository.loadTasks();
        Task task = findTask(tasks, id);
        task.updateDescription(description);
        repository.saveTasks(tasks);
    }

    @Override
    public void delete(int id) {
        List<Task> tasks = repository.loadTasks();
        tasks.removeIf((s) -> s.getId() == id);
        repository.saveTasks(tasks);
    }

    @Override
    public void markInProgress(int id) {
        List<Task> tasks = repository.loadTasks();
        findTask(tasks, id).markAsInProgress();
        repository.saveTasks(tasks);
    }


    @Override
    public void markDone(int id) {
        List<Task> tasks = repository.loadTasks();
        findTask(tasks,id).markAsDone();
        repository.saveTasks(tasks);
    }

    @Override
    public List<Task> list() {
        return repository.loadTasks();
    }

    private static Task findTask(List<Task> tasks, int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchTaskException(String.format("Task with ID %d not found", id)));
    }
}
