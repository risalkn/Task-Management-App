package task.management.TaskManagementApp.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.management.TaskManagementApp.model.Task;
import task.management.TaskManagementApp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;


    /**
     * get all task
     *
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @GetMapping
    public ResponseEntity<Object> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }


    /**
     * create task
     *
     * @param task task
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @PostMapping("/createTask")
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    /**
     * create tasks
     *
     * @param task task
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @PostMapping("/createTasks")
    public ResponseEntity<Object> createTasks(@RequestBody List<Task> task) {
        return new ResponseEntity<>(taskService.createTasks(task), HttpStatus.CREATED);
    }

    /**
     * get task by id
     *
     * @param id id
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable("id") long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    /**
     * delete task by id
     *
     * @param id id
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable("id") long id) {
        return new ResponseEntity<>("Task deleted " + taskService.deleteTaskById(id), HttpStatus.OK);
    }

    /**
     * update task by id
     *
     * @param id   id
     * @param task task
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see Object
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateTaskById(@PathVariable("id") long id, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }
}
