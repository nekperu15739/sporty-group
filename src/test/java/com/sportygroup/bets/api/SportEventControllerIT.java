package com.sportygroup.bets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.sportygroup.bets.service.usecase.SportEventAddUseCase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(SportEventController.class)
class SportEventControllerIT {

    private static final Faker faker = new Faker();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SportEventAddUseCase addUseCase;

    @Test
    @SneakyThrows
    void addNewSportEvent() {
        var resource = buildResource();

        doNothing().when(addUseCase).addNewSportEvent(any(SportEventResource.class));

        mockMvc.perform(post("/sport-event")
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(resource)))
            .andDo(print())
            .andExpect(status().isAccepted())
            .andExpect(jsonPath("$.id").value(resource.getId().toString()))
            .andExpect(jsonPath("$.name").value(resource.getName()))
            .andExpect(jsonPath("$.winnerId").value(resource.getWinnerId().toString()));

        verify(addUseCase).addNewSportEvent(any(SportEventResource.class));
    }

    private SportEventResource buildResource() {
        return SportEventResource
            .builder()
            .name(faker.name().name())
            .id(UUID.randomUUID())
            .winnerId(UUID.randomUUID())
            .build();
    }
}
