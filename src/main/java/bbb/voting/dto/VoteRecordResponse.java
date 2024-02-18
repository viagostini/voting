package bbb.voting.dto;

import bbb.voting.entity.VoteRecord;
import bbb.voting.entity.VoteRecordStatus;
import java.time.Instant;

public record VoteRecordResponse(VoteRecordStatus status, Instant timestamp) {
    public static VoteRecordResponse from(VoteRecord voteRecord) {
        return new VoteRecordResponse(voteRecord.getStatus(), voteRecord.getCreatedAt());
    }
}
