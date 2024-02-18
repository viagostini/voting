package bbb.voting;

import bbb.voting.entity.Candidate;
import bbb.voting.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class VotingApplication implements CommandLineRunner {
	private final CandidateRepository candidateRepository;

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Candidate candidate = new Candidate("Superman");
		Candidate candidate2 = new Candidate("Batman");
		Candidate candidate3 = new Candidate("Spider-Man");

		candidateRepository.saveAll(List.of(candidate, candidate2, candidate3));

		log.info("Votes:");
		for (Candidate c : candidateRepository.findAll()) {
			log.info(c.getId() + ": " + c.getName() + " - " + c.getVotes() + " votes");
		}
	}
}
