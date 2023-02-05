package task.management.TaskManagementApp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import task.management.TaskManagementApp.SlowTest;
import task.management.TaskManagementApp.exception.TaskNotFoundException;
import task.management.TaskManagementApp.model.Task;
import task.management.TaskManagementApp.repository.TaskRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@SlowTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TaskRepository repository;

    private List<Task> tasksList;

    private Long taskId = Long.valueOf(10015);


    private void Tasks_Are_CreatedCorrectly() throws Exception {
        Task task = new Task(1005L, "Hello, World!", "Hello world task I need to complete.", "2021-10-12T13:54:34.123Z", "2021-11-02T09:00:00.000Z", "NotStarted");
        taskId =
                mapper
                        .readValue(mockMvc.perform(post("/task/createTask")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(task)))
                                .andExpect(status().isCreated())
                                .andReturn().getResponse().getContentAsString(), Task.class).getId();

    }

    @Test
    @DisplayName("When all Task are requested then they are all returned")
    void allTaskRequested() throws Exception {
        Tasks_Are_CreatedCorrectly();
        mockMvc
                .perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Hello, World!")));
    }


    @Test
    @DisplayName("When a task details access With valid  task ID")
    void taskRetriedByID() throws Exception {
        Tasks_Are_CreatedCorrectly();
        mockMvc
                .perform(get("/task/" + taskId))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("name", equalTo("Hello, World!")));

        Task task1 = new Task(taskId, "Hello, World!", "Hello world task I need to complete.", "2021-10-12T13:54:34.123Z", "2021-11-02T09:00:00.000Z", "NotStarted");
        assertThat(
                repository
                        .findById(taskId)
                        .orElseThrow(
                                () -> new TaskNotFoundException("Task has been not found with id " + taskId)),
                equalTo(task1));
    }

    @Test
    @DisplayName("When a task details access With Invalid task ID")
    void taskRetriedWithInvalidID() throws Exception {
        mockMvc
                .perform(get("/task/10006"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("When a Task deleted  With valid  Task ID")
    void taskDeletedWithID() throws Exception {
        Tasks_Are_CreatedCorrectly();
        mockMvc
                .perform(delete("/task/" + taskId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("When a task deleted  With Invalid  task ID")
    void taskDeletedWithInvalidID() throws Exception {
        mockMvc
                .perform(delete("/task/100006"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

}
