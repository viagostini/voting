package bbb.voting.controller;

import bbb.voting.dto.VoteRecordResponse;
import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.VoteRecordRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller exposes a way for us to inspect the votes recorded in the application.
 * This is mainly included to learn how to use paging and to inspect the records table.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class VoteRecordController {
    private final VoteRecordRepository voteRecordRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVoteRecords(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<VoteRecord> pageVoteRecords = voteRecordRepository.findAll(PageRequest.of(page, size));

        Map<String, Object> response = Map.of(
                "voteRecords", pageVoteRecords.getContent().stream().map(VoteRecordResponse::from),
                "currentPage", pageVoteRecords.getNumber(),
                "totalItems", pageVoteRecords.getTotalElements(),
                "totalPages", pageVoteRecords.getTotalPages()
            );

        return ResponseEntity.ok(response);
    }
}
