package bbb.voting.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import bbb.voting.dto.VoteRecordResponse;
import bbb.voting.entity.Candidate;
import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.VoteRecordStatus;
import bbb.voting.repository.CandidateRepository;
import bbb.voting.service.VoteRegisteringService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureJsonTesters
@WebMvcTest(CandidateController.class)
public class CandidateControllerTests {
    @MockBean
    private CandidateRepository candidateRepository;

    @MockBean
    private VoteRegisteringService voteRegisteringService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<VoteRecordResponse> voteRecordResponseJson;

    @Test
    public void voteForCandidate() throws Exception {
        // given
        Candidate candidate = new Candidate(1L, "Spider Man", 0);
        VoteRecord voteRecord = new VoteRecord(1L, 1L, VoteRecordStatus.PENDING, Instant.now(), Instant.now());

        given(candidateRepository.findById(1L)).willReturn(Optional.of(candidate));
        given(voteRegisteringService.registerVote(1L)).willReturn(voteRecord);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/candidates/1/vote")).andReturn().getResponse();

        // then
        String expectedJson = voteRecordResponseJson.write(VoteRecordResponse.from(voteRecord)).getJson();
        then(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
        then(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
