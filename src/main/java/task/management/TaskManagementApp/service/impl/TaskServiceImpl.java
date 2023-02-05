package task.management.TaskManagementApp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.management.TaskManagementApp.exception.TaskNotFoundException;
import task.management.TaskManagementApp.model.Task;
import task.management.TaskManagementApp.repository.TaskRepository;
import task.management.TaskManagementApp.service.TaskService;

import java.util.List;

/**
 * task service impl
 */
@Service
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * get task by id
     *
     * @param taskId taskId
     * @return {@link Task}
     * @see Task
     */
    @Override
    public Task getTaskById(Long taskId) {
        if (!taskRepository.existsById(taskId))
            new TaskNotFoundException("Task has been not found with id " + taskId);

        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task has been not found with id " + taskId));
    }

    /**
     * delete task by id
     *
     * @param taskId taskId
     * @return {@link boolean}
     */
    @Override
    public boolean deleteTaskById(Long taskId) {
        taskRepository.delete(taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id " + taskId)));
        return true;
    }

    /**
     * create task
     *
     * @param task task
     * @return {@link Task}
     * @see Task
     */
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * create tasks
     *
     * @param task task
     * @return {@link List}
     * @see List
     * @see Task
     */
    @Override
    public List<Task> createTasks(List<Task> task) {
        return taskRepository.saveAll(task);
    }

    /**
     * update task
     *
     * @param id   id
     * @param task task
     * @return {@link Task}
     * @see Task
     */
    @Override
    public Task updateTask(long id, Task task) {
        Task task_item = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task has been not found with id " + id));
        task_item.setName(task.getName());
        task_item.setDescription(task.getDescription());
        task_item.setDueDate(task.getDueDate());
        task_item.setCreatedAt(task.getCreatedAt());
        task_item.setStatus(task.getStatus());
        return taskRepository.save(task_item);

    }
}
