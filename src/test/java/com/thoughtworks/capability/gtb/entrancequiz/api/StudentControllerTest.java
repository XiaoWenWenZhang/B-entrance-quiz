package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.bo.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void endUp() {
    }
    @Test
    public void should_add_students_success() throws Exception {
        Student student = new Student(1, "小美");

        String studentString = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/add-stydent").contentType(MediaType.APPLICATION_JSON).content(studentString))
                .andExpect(status().isCreated());
    }


}