package bbb.voting.service;

import bbb.voting.entity.VoteRecord;
import bbb.voting.repository.VoteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteRegisteringService {

    private final VoteRecordRepository voteRecordRepository;

    public VoteRecord registerVote(Long id) {
        return voteRecordRepository.save(new VoteRecord(id));
    }
}
