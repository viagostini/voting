package bbb.voting.repository;

import bbb.voting.entity.VoteRecord;
import org.springframework.data.repository.CrudRepository;

public interface VoteRecordRepository extends CrudRepository<VoteRecord, Long> {
}
