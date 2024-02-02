package bbb.voting.controller;

import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.VoteRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/vote-records")
public class VoteRecordController {
    private final VoteRecordRepository voteRecordRepository;

    public VoteRecordController(VoteRecordRepository voteRecordRepository) {
        this.voteRecordRepository = voteRecordRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVoteRecords(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<VoteRecord> pageVoteRecords = voteRecordRepository.findAll(PageRequest.of(page, size));

        Map<String, Object> response = Map.of(
            "voteRecords", pageVoteRecords.getContent(),
            "currentPage", pageVoteRecords.getNumber(),
            "totalItems", pageVoteRecords.getTotalElements(),
            "totalPages", pageVoteRecords.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }
}
