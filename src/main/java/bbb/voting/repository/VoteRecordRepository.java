package bbb.voting.repository;

import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.VoteRecordStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRecordRepository extends CrudRepository<VoteRecord, Long> {
    List<VoteRecord> findByStatus(VoteRecordStatus pending);
}
