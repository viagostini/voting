package bbb.voting.controller;

import bbb.voting.dto.VoteResponse;
import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.VoteRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class VotesController {
    private final VoteRecordRepository voteRecordRepository;

    public VotesController(VoteRecordRepository voteRecordRepository) {
        this.voteRecordRepository = voteRecordRepository;
    }

    @GetMapping("/api/vote/{id}")
    public ResponseEntity<VoteResponse> vote(@PathVariable Long id) {
        String clientIpAddress = getClientAddress();
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Vote for " + id + " registered";

        voteRecordRepository.save(new VoteRecord(id, clientIpAddress, timestamp));

        VoteResponse response = new VoteResponse(clientIpAddress, timestamp, message);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/votes")
    public Iterable<VoteRecord> getVotes() {
        return voteRecordRepository.findAll();
    }

    private String getClientAddress() {
        // Placeholder for a real implementation
        return "127.0.0.1";
    }
}
