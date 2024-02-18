package bbb.voting.service;

import bbb.voting.entity.Candidate;
import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.CandidateRepository;
import bbb.voting.repository.VoteRecordRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VoteRecordProcessorService {
    private final CandidateRepository candidateRepository;

    private final VoteRecordRepository voteRecordRepository;

    private final Counter processedVotesCounter;

    public VoteRecordProcessorService(CandidateRepository candidateRepository,
                                      VoteRecordRepository voteRecordRepository,
                                      MeterRegistry meterRegistry) {

        this.candidateRepository = candidateRepository;
        this.voteRecordRepository = voteRecordRepository;
        this.processedVotesCounter = Counter.builder("scheduled.task.processPendingVotes.processedVotes")
                                            .description("Number of processed votes")
                                            .register(meterRegistry);
    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    @Timed(
        value = "scheduled.task.processPendingVotes.execution.time",
        description = "Time taken to process pending votes",
        histogram = true
    )
    @Transactional
    public void processPendingVotes() {
        List<VoteRecord> pendingVotes = voteRecordRepository.findPendingVotes();

        for (VoteRecord voteRecord : pendingVotes) {
            Candidate candidate = candidateRepository.findById(voteRecord.getCandidateId()).orElseThrow();
            candidate.incrementVotes();
            candidateRepository.save(candidate);

            voteRecord.markAsProcessed();
            voteRecordRepository.save(voteRecord);
        }

        if (!pendingVotes.isEmpty()) {
            processedVotesCounter.increment(pendingVotes.size());
            log.info("Processed " + pendingVotes.size() + " pending votes");
        }
    }
}
