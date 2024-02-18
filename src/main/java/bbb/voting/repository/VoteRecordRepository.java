package bbb.voting.repository;

import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.VoteRecordStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRecordRepository extends CrudRepository<VoteRecord, Long> {
    Page<VoteRecord> findAll(Pageable pageable);

    @Query("SELECT v FROM VoteRecord v WHERE v.status = 'PENDING'")
    List<VoteRecord> findPendingVotes();
}
