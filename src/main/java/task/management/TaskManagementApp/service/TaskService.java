package task.management.TaskManagementApp.service;


import org.springframework.stereotype.Service;
import task.management.TaskManagementApp.model.Task;

import java.util.List;

@Service
public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    boolean deleteTaskById(Long hotelId);

    Task createTask(Task task);

    List<Task> createTasks(List<Task> task);

    Task updateTask(long id, Task author);

}
