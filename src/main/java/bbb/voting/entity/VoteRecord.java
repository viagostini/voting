package bbb.voting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteRecord {
    @Id
    @GeneratedValue
    private Long id;

    private Long candidateId;

    @Setter
    @Enumerated(EnumType.STRING)
    private VoteRecordStatus status;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public VoteRecord(Long candidateId) {
        this.candidateId = candidateId;
        this.status = VoteRecordStatus.PENDING;
    }

    public void markAsProcessed() {
        this.status = VoteRecordStatus.PROCESSED;
    }
}
