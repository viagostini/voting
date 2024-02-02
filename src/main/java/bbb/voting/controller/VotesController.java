package bbb.voting.controller;

import bbb.voting.dto.VoteResponse;
import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.Votes;
import bbb.voting.repository.VoteRecordRepository;
import bbb.voting.repository.VotesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/vote")
public class VotesController {
    private final VoteRecordRepository voteRecordRepository;
    private final VotesRepository votesRepository;

    public VotesController(VoteRecordRepository voteRecordRepository, VotesRepository votesRepository) {
        this.voteRecordRepository = voteRecordRepository;
        this.votesRepository = votesRepository;
    }

    @GetMapping
    public Iterable<Votes> getVotes() {
        return votesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteResponse> vote(@PathVariable Long id) {
        String clientIpAddress = getClientAddress();
        LocalDateTime timestamp = LocalDateTime.now();

        boolean candidateExists = votesRepository.existsById(id);

        if (!candidateExists) {
            String message = "Candidate with id " + id + " does not exist";
            VoteResponse response = new VoteResponse(clientIpAddress, timestamp, message);
            return ResponseEntity.badRequest().body(response);
        }

        String message = "Vote for " + id + " registered";

        voteRecordRepository.save(new VoteRecord(id, clientIpAddress, timestamp));

        VoteResponse response = new VoteResponse(clientIpAddress, timestamp, message);
        return ResponseEntity.accepted().body(response);
    }

    private String getClientAddress() {
        // Placeholder for a real implementation
        return "127.0.0.1";
    }
}
