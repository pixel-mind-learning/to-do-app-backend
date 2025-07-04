package com.coveragex.todo.intergrationTest;

import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTask() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Take Home Assignment");
        dto.setDescription("Finish the mid term assignment");

        mockMvc.perform(post("/api/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Task is created successfully."));
    }

    @Test
    void testGetAllPendingTodos() throws Exception {
        mockMvc.perform(get("/api/task/get-all-pending-todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All pending todos fetched successfully."))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testMakeToDoCompleted() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Mark Complete Task");
        dto.setDescription("To be marked as complete");

        mockMvc.perform(post("/api/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/task/make-todo-completed")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Task Completed."));
    }
}
