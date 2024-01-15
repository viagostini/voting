package bbb.voting.controller;

import bbb.voting.repository.VotesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VotesController.class)
class VotesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotesRepository votesRepository;

    @Test
    public void testVoteEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vote/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientIpAddress").value("127.0.0.1"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

}