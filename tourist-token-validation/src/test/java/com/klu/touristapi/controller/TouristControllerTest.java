package com.klu.touristapi.controller;

import com.klu.touristapi.model.Tourist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validTokenReturnsTourist() throws Exception {
        mockMvc.perform(get("/tourists/1").header("X-Auth-Token", "BORDER123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    void invalidTokenReturnsErrorDetails() throws Exception {
        mockMvc.perform(get("/tourists/1").header("X-Auth-Token", "WRONG123"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Invalid X-Auth-Token value"));
    }

    @Test
    void missingTokenReturnsErrorDetails() throws Exception {
        mockMvc.perform(get("/tourists/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Missing X-Auth-Token header"));
    }

    @Test
    void unknownTouristReturns404() throws Exception {
        mockMvc.perform(get("/tourists/99").header("X-Auth-Token", "BORDER123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Tourist with ID 99 was not found"));
    }
}
