package bbb.voting.controller;

import bbb.voting.dto.CreateCandidateRequest;
import bbb.voting.dto.VoteRecordResponse;
import bbb.voting.entity.Candidate;
import bbb.voting.entity.VoteRecord;
import bbb.voting.exception.CandidateNotFoundException;
import bbb.voting.repository.CandidateRepository;
import bbb.voting.service.VoteRegisteringService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This handles creating Candidates as well as voting for a candidate.
 */
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateRepository candidateRepository;
    private final VoteRegisteringService voteRegisteringService;

    @GetMapping("/{id}/vote")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VoteRecordResponse voteForCandidate(@PathVariable long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if (candidate.isEmpty()) {
            throw new CandidateNotFoundException(id);
        }

        VoteRecord voteRecord = voteRegisteringService.registerVote(id);
        return VoteRecordResponse.from(voteRecord);
    }

    @GetMapping
    public Iterable<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Candidate> getCandidate(@PathVariable long id) {
        return candidateRepository.findById(id);
    }

    @PostMapping
    public Candidate addCandidate(@RequestBody CreateCandidateRequest candidate) {
        return candidateRepository.save(new Candidate(candidate.name()));
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Candidate not found")
    public void handleCandidateNotFound() {
    }
}
