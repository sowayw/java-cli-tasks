package Service;

import model.Task;
import java.util.List;

public interface TaskService {

    void add(String description);
    void update(int id, String description);
    void delete(int id);

    void markDone(int id);
    void markInProgress(int id);

    List<Task> list();

}
