package bbb.voting.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoteRecordStatus {
    PENDING("PENDING"), PROCESSED("PROCESSED");

    private final String status;
}
