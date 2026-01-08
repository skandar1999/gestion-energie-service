package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
class GestionEnergieEauApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.data.mongodb.uri",
                mongoDBContainer::getReplicaSetUrl
        );
    }

    @Test
    void shouldCreatePompe() throws Exception {

        String pompeJson = """
            {
              "reference": "PXT-001",
              "puissance": 17.5,
              "statut": "OFF",
              "dateMiseEnService": "2024-01-10"
            }
            """;

        mockMvc.perform(
                post("/api/pompes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pompeJson)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.reference").value("PMP-001"))
        .andExpect(jsonPath("$.puissance").value(12.5))
        .andExpect(jsonPath("$.statut").value("ON"));
    }

}
