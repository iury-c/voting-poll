package com.demoapp.votingpoll.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class VoteDto {

    @NotBlank(message = "Cpf should not be blank.")
    private String cpf;

    @Positive(message = "Session Id should be greater than 0.")
    private int sessionId;

    @NotBlank(message = "Vote should not be blank.")
    private String voteType;
}
