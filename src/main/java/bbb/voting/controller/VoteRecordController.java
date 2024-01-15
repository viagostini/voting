package bbb.voting.controller;

import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.VoteRecordRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote-records")
public class VoteRecordController {
    private final VoteRecordRepository voteRecordRepository;

    public VoteRecordController(VoteRecordRepository voteRecordRepository) {
        this.voteRecordRepository = voteRecordRepository;
    }

    @GetMapping
    public Iterable<VoteRecord> getVoteRecords() {
        return voteRecordRepository.findAll();
    }
}
