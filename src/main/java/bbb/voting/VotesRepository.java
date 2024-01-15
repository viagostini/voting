package bbb.voting;

import bbb.voting.entity.Votes;
import org.springframework.data.repository.CrudRepository;

public interface VotesRepository extends CrudRepository<Votes, Long> {
}
