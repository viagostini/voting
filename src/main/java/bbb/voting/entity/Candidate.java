package bbb.voting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private long votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public void incrementVotes() {
        this.votes++;
    }
}
