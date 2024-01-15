package bbb.voting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long candidateId;

    private VoteRecordStatus status;

    private String clientIpAddress;

    private LocalDateTime timestamp;

    public VoteRecord() {
    }

   public VoteRecord(Long candidateId, String clientIpAddress, LocalDateTime timestamp) {
        this.candidateId = candidateId;
        this.status = VoteRecordStatus.PENDING;
        this.clientIpAddress = clientIpAddress;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public VoteRecordStatus getStatus() {
        return status;
    }

    public void setStatus(VoteRecordStatus status) {
        this.status = status;
    }
}
