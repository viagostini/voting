package bbb.voting.dto;

import java.time.LocalDateTime;

public record VoteResponse(String clientIpAddress, LocalDateTime timestamp, String message) {

}
