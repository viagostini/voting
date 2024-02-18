package bbb.voting.repository;

import bbb.voting.entity.Candidate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
