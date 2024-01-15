package bbb.voting.controller;

import bbb.voting.dto.VoteResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;

@RestController
public class VotesController {

    @GetMapping("/api/vote/{id}")
    public ResponseEntity<VoteResponse> vote(@PathVariable Long id) {
        String clientIpAddress = getClientAddress();
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Vote for " + id + " registered";

        VoteResponse response = new VoteResponse(clientIpAddress, timestamp, message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String getClientAddress() {
        // Placeholder for a real implementation
        return "127.0.0.1";
    }
}
