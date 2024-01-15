package bbb.voting;

import bbb.voting.entity.Votes;
import bbb.voting.repository.VotesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class VotingApplication implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(VotingApplication.class.getName());

	private final VotesRepository votesRepository;

	public VotingApplication(VotesRepository votesRepository) {
		this.votesRepository = votesRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Votes votes = new Votes("Superman", 0L);
		Votes votes2 = new Votes("Batman", 0L);
		Votes votes3 = new Votes("Spider-man", 0L);

		votesRepository.saveAll(List.of(votes, votes2, votes3));

		logger.info("Votes:");
		for (Votes v : votesRepository.findAll()) {
			logger.info(v.getId() + ": " + v.getName() + " - " + v.getVotes() + " votes");
		}
	}
}
