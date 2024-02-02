package bbb.voting.service;

import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.VoteRecordStatus;
import bbb.voting.entity.Votes;
import bbb.voting.repository.VoteRecordRepository;
import bbb.voting.repository.VotesRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class VoteRecordProcessorService {
    private static final Logger logger = Logger.getLogger(VoteRecordProcessorService.class.getName());

    private final VotesRepository votesRepository;
    private final VoteRecordRepository voteRecordRepository;

    private final Counter processedVotesCounter;

    public VoteRecordProcessorService(VotesRepository votesRepository, VoteRecordRepository voteRecordRepository, MeterRegistry meterRegistry) {
        this.votesRepository = votesRepository;
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
        List<VoteRecord> pendingVotes = voteRecordRepository.findByStatus(VoteRecordStatus.PENDING);

        for (VoteRecord voteRecord : pendingVotes) {
            Votes votes = votesRepository.findById(voteRecord.getCandidateId()).orElseThrow();
            votes.setVotes(votes.getVotes() + 1);
            votesRepository.save(votes);

            voteRecord.setStatus(VoteRecordStatus.PROCESSED);
            voteRecordRepository.save(voteRecord);
        }

        if (!pendingVotes.isEmpty()) {
            processedVotesCounter.increment(pendingVotes.size());
            logger.info("Processed " + pendingVotes.size() + " pending votes");
        }
    }
}
