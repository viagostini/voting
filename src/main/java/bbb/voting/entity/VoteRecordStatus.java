package bbb.voting.entity;

public enum VoteRecordStatus {
    PENDING("PENDING"),
    PROCESSED("PROCESSED");

    private final String status;

    VoteRecordStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
